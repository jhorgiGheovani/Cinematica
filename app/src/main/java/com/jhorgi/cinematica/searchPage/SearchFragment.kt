package com.jhorgi.cinematica.searchPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.jhorgi.cinematica.R
import com.jhorgi.cinematica.commonAdapter.movieListAdapterV1.MovieListAdapterV1
import com.jhorgi.cinematica.commonAdapter.tvSeriesListAdapterV1.TvSeriesListAdapterV1
import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.databinding.FragmentSearchBinding
import com.jhorgi.cinematica.details.DetailsActivity
import com.jhorgi.cinematica.searchPage.adapter.SearchResultSectionsPagerAdapter
import com.jhorgi.cinematica.seeAllPages.SeeAllActivity
import com.jhorgi.cinematica.seeAllPages.SeeAllActivity.Companion.TYPE_TITTLE_DATA
import com.jhorgi.cinematica.seeAllPages.SeeAllContentFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

//@FlowPreview
//@ExperimentalCoroutinesApi
class SearchFragment : Fragment() {
    private val searchPageViewModel: SearchPageViewModel by viewModel()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val discoverMovieLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.discoverContentLayout.movieDiscoverRV.layoutManager = discoverMovieLayoutManager

        val discoverTvShowLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.discoverContentLayout.tvShowDiscoverRV.layoutManager = discoverTvShowLayoutManager

//        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        binding.searchResultLayout.rv.layoutManager = layoutManager


        searchPageViewModel.getDiscoverMovieList()
        searchPageViewModel.getDiscoverTvList()

        //Enter to see all page when user click see all pages
        binding.discoverContentLayout.seeAllLabel.setOnClickListener {
            val intent = Intent(activity, SeeAllActivity::class.java)
            intent.putExtra(TYPE_TITTLE_DATA, SeeAllContentFragment.DISCOVERY)
            startActivity(intent)
        }

        fetchDiscoverMovies()
        fetchDiscoverTvSeries()






//        searchPageViewModel.searchResult.observe(viewLifecycleOwner) {
//            lifecycleScope.launch {
//                it.collect { data ->
//                    when (data) {
//                        is Resource.Success -> {
////                            binding.discoverContentLayout2.textView2.text = data.data.toString()
//                            Toast.makeText(
//                                context,
//                                data.data[0].movieId.toString(),
//                                Toast.LENGTH_SHORT
//                            ).show()
//                            val adapter = SearchResultAdapter(data.data) { clickMovie ->
//                                val intent = Intent(activity, DetailsActivity::class.java)
//                                intent.putExtra(DetailsActivity.EXTRA_DATA, clickMovie.movieId)
//                                intent.putExtra(
//                                    DetailsActivity.TYPE_DATA,
//                                    DetailsActivity.MOVIE_TYPE
//                                )
//                                startActivity(intent)
//                            }
//
//                            binding.searchResultLayout.rv.adapter = adapter
//
//                        }
//
//                        is Resource.Error -> {
////                            Toast.makeText(context,"enter error", Toast.LENGTH_LONG).show()
//                        }
//
//                    }
//                }
//            }
//        }


        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //when user click submit in search
                lifecycleScope.launch {
                    searchPageViewModel.queryChannel.value = query.toString()
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty()) {
                    binding.discoveryContentContainer.visibility = View.GONE
                    binding.searchResultsContainer.visibility = View.VISIBLE
                    //tab layout



                    val sectionsPagerAdapter = SearchResultSectionsPagerAdapter(requireActivity())
                    val viewPager = binding.searchResultLayout.viewPager
                    viewPager.adapter = sectionsPagerAdapter

                    val tabs = binding.searchResultLayout.tabs
                    TabLayoutMediator(tabs,viewPager){tab,position ->
                        tab.text = resources.getString(TAB_TITLES[position])
                    }.attach()


                } else {
                    binding.discoveryContentContainer.visibility = View.VISIBLE
                    binding.searchResultsContainer.visibility = View.GONE
                }

                //when user typing
                lifecycleScope.launch {
                    searchPageViewModel.queryChannel.value = newText
                }
                return true
            }
        })


    }


    private fun fetchDiscoverMovies() {
        searchPageViewModel.discoverMovie.observe(viewLifecycleOwner) { movie ->
            when (movie) {
                is Resource.Success -> {
                    binding.tvShowProgressBar.visibility = View.GONE
                    binding.discoveryContentContainer.visibility = View.VISIBLE
                    val movieAdapter = MovieListAdapterV1(movie.data) { clickMovie ->
                        val intent = Intent(activity, DetailsActivity::class.java)
                        intent.putExtra(DetailsActivity.EXTRA_DATA, clickMovie.movieId)
                        intent.putExtra(DetailsActivity.TYPE_DATA, DetailsActivity.MOVIE_TYPE)
                        startActivity(intent)
                    }
                    binding.discoverContentLayout.movieDiscoverRV.adapter = movieAdapter
                }

                is Resource.Error -> {
                    binding.tvShowProgressBar.visibility = View.GONE
                    binding.discoveryContentContainer.visibility = View.VISIBLE
                    binding.discoverContentLayout.errorMsgDiscoverMovie.visibility = View.VISIBLE
                    binding.discoverContentLayout.errorMsgDiscoverMovie.text = movie.error
//                    Toast.makeText(context, movie.error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun fetchDiscoverTvSeries() {
        searchPageViewModel.discoverTvList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val tvShowAdapter = TvSeriesListAdapterV1(it.data) { clickTvShow ->
                        val intent = Intent(activity, DetailsActivity::class.java)
                        intent.putExtra(DetailsActivity.EXTRA_DATA, clickTvShow.id)
                        intent.putExtra(DetailsActivity.TYPE_DATA, DetailsActivity.TV_SERIES_TYPE)
                        startActivity(intent)
                    }
                    binding.discoverContentLayout.tvShowDiscoverRV.adapter = tvShowAdapter
                }

                is Resource.Error -> {
                    binding.tvShowProgressBar.visibility = View.GONE
                    binding.discoveryContentContainer.visibility = View.VISIBLE
                    binding.discoverContentLayout.errorMsgDiscoverTvseries.visibility = View.VISIBLE
                    binding.discoverContentLayout.errorMsgDiscoverTvseries.text = it.error
                    Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                }
            }

        }

    }


    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}
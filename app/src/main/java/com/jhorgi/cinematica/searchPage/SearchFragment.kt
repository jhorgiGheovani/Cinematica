package com.jhorgi.cinematica.searchPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhorgi.cinematica.common.movieListAdapter.MovieAdapter
import com.jhorgi.cinematica.common.tvListAdapter.TvListAdapter
import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.databinding.FragmentSearchBinding
import com.jhorgi.cinematica.details.DetailsActivity
import com.jhorgi.cinematica.seeAllPages.SeeAllActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


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
        binding.contentLayout.movieDiscoverRV.layoutManager = discoverMovieLayoutManager

        val discoverTvShowLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.contentLayout.tvShowDiscoverRV.layoutManager = discoverTvShowLayoutManager

        searchPageViewModel.getDiscoverMovieList()
        searchPageViewModel.getDiscoverTvList()

        //Enter to see all page when user click see all pages
        binding.contentLayout.seeAllLabel.setOnClickListener {
            val intent = Intent(activity, SeeAllActivity::class.java)
            intent.putExtra(TYPE_TITTLE_DATA, DISCOVER)
            startActivity(intent)
        }

        bindDiscoverMovie()
        bindDiscoverTvShow()



        binding.searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                //click search
                binding.discoveryListLayout.visibility = View.GONE
                binding.searchResultsLayout.visibility = View.VISIBLE
            } else {
                //not click search
                binding.discoveryListLayout.visibility = View.VISIBLE
                binding.searchResultsLayout.visibility = View.GONE
            }
        }
    }


    private fun bindDiscoverMovie() {
        searchPageViewModel.discoverMovie.observe(viewLifecycleOwner) { movie ->
            when (movie) {
                is Resource.Success -> {
                    binding.tvShowProgressBar.visibility = View.GONE
                    binding.discoveryListLayout.visibility = View.VISIBLE
                    val movieAdapter = MovieAdapter(movie.data) { clickMovie ->
                        val intent = Intent(activity, DetailsActivity::class.java)
                        intent.putExtra(DetailsActivity.EXTRA_DATA, clickMovie.movieId)
                        intent.putExtra(DetailsActivity.TYPE_DATA, DetailsActivity.MOVIE_TYPE)
                        startActivity(intent)
                    }
                    binding.contentLayout.movieDiscoverRV.adapter = movieAdapter
                }

                is Resource.Error -> {
                    binding.tvShowProgressBar.visibility = View.GONE
                    binding.discoveryListLayout.visibility = View.VISIBLE
                    binding.contentLayout.errorMsgDiscoverMovie.visibility = View.VISIBLE
                    binding.contentLayout.errorMsgDiscoverMovie.text = movie.error
//                    Toast.makeText(context, movie.error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun bindDiscoverTvShow(){
        searchPageViewModel.discoverTvList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val tvShowAdapter = TvListAdapter(it.data) { clickTvShow ->
                        val intent = Intent(activity, DetailsActivity::class.java)
                        intent.putExtra(DetailsActivity.EXTRA_DATA, clickTvShow.id)
                        intent.putExtra(DetailsActivity.TYPE_DATA, DetailsActivity.TV_SERIES_TYPE)
                        startActivity(intent)
                    }
                    binding.contentLayout.tvShowDiscoverRV.adapter = tvShowAdapter
                }
                is Resource.Error -> {
                    binding.tvShowProgressBar.visibility = View.GONE
                    binding.discoveryListLayout.visibility = View.VISIBLE
                    binding.contentLayout.errorMsgDiscoverTvseries.visibility= View.VISIBLE
                    binding.contentLayout.errorMsgDiscoverTvseries.text = it.error
                    Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                }
            }

        }

    }


    companion object {
        const val TYPE_TITTLE_DATA = "type_data"
        const val DISCOVER = "Discover"
        const val TRENDING = "Trending"
    }
}
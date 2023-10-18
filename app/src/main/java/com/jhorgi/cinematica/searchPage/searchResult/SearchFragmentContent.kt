package com.jhorgi.cinematica.searchPage.searchResult

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jhorgi.cinematica.commonAdapter.listAdapterListV2.ListAdapterV2
import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.core.utils.DataMapper
import com.jhorgi.cinematica.databinding.FragmentSearchContentBinding
import com.jhorgi.cinematica.details.DetailsActivity
import com.jhorgi.cinematica.searchPage.SearchPageViewModel
import com.jhorgi.cinematica.searchPage.adapter.SearchResultSectionsPagerAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class SearchFragmentContent : Fragment() {

    private val searchPageViewModel: SearchPageViewModel by viewModel()

    private var _binding: FragmentSearchContentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvContent.layoutManager = layoutManager

        val index = arguments?.getInt(SearchResultSectionsPagerAdapter.ARG_SECTION_NUMBER, 0)
        val query = arguments?.getString(SearchResultSectionsPagerAdapter.QUERY)

        if (index == 1) {
            searchPageViewModel.getSearchResult(query.toString()).observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> {
                        binding.shimmerEffect.visibility = View.GONE

                        val data = DataMapper.mapMovieToRecyclerViewDataList2(it.data)

                        if (data.isNotEmpty()) {
                            val adapter = ListAdapterV2(data) { clickMovie ->
                                val intent = Intent(activity, DetailsActivity::class.java)
                                intent.putExtra(DetailsActivity.EXTRA_DATA, clickMovie.id)
                                intent.putExtra(
                                    DetailsActivity.TYPE_DATA,
                                    DetailsActivity.MOVIE_TYPE
                                )
                                startActivity(intent)
                            }
                            binding.rvContent.adapter = adapter
                        }
                        if (data.isEmpty()) {
                            binding.animationView.setAnimation("emptyAnimation.lottie")
                            binding.animationView.playAnimation()
                            Snackbar.make(view, "Oops! We couldn't find any content that match with that tittle", Snackbar.LENGTH_LONG).show()
                        }


                    }

                    is Resource.Error -> {
                        Snackbar.make(view, it.error, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }

        }
        if (index == 2) {
            searchPageViewModel.getTvSeriesSearchResult(query.toString())
                .observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Success -> {
                            binding.shimmerEffect.visibility = View.GONE
                            val data = DataMapper.mapTvSeriesToRecyclerViewDataList2(it.data)



                            if(data.isNotEmpty()){
                                val adapter = ListAdapterV2(data) { clickMovie ->
                                    val intent = Intent(activity, DetailsActivity::class.java)
                                    intent.putExtra(DetailsActivity.EXTRA_DATA, clickMovie.id)
                                    intent.putExtra(
                                        DetailsActivity.TYPE_DATA,
                                        DetailsActivity.TV_SERIES_TYPE
                                    )
                                    startActivity(intent)
                                }
                                binding.rvContent.adapter = adapter
                            }
                            if (data.isEmpty()) {
                                binding.animationView.setAnimation("emptyAnimation.lottie")
                                binding.animationView.playAnimation()
                                Snackbar.make(view, "Oops! We couldn't find any content that match with that tittle", Snackbar.LENGTH_LONG).show()
                            }
                        }

                        is Resource.Error -> {
                            Snackbar.make(view, it.error, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
        }

    }

}
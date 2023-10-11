package com.jhorgi.cinematica.seeAllPages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhorgi.cinematica.commonAdapter.movieSeeAllAdapter.LoadingStateAdapter
import com.jhorgi.cinematica.commonAdapter.movieSeeAllAdapter.MoviePagingAdapter
import com.jhorgi.cinematica.commonAdapter.tvSeriesSeeAllAdapter.TvSeriesPagingAdapter
import com.jhorgi.cinematica.databinding.FragmentSeeAllContentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SeeAllContentFragment : Fragment() {

    private val seeAllPagesViewModel: SeeAllPagesViewModel by viewModel()
    private var _binding: FragmentSeeAllContentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeeAllContentBinding.inflate(inflater, container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val index = arguments?.getInt(ARG_SECTION_NUMBER,0)
        val type = arguments?.getString(ARG_TYPE)

        Log.d("tipe", type.toString())


        if(index==1){
            val moviePagingAdapter = MoviePagingAdapter()

            binding.rvSeeAllFragment.adapter = moviePagingAdapter.withLoadStateHeaderAndFooter(
                footer = LoadingStateAdapter{
                    moviePagingAdapter.retry()
                },
                header = LoadingStateAdapter{
                    moviePagingAdapter.retry()
                }
            )

            if(type == DISCOVERY){
                viewLifecycleOwner.lifecycleScope.launch {
                    seeAllPagesViewModel.discoverMovie.collectLatest { resourcePagingData->
                        moviePagingAdapter.submitData(resourcePagingData)
                    }
                }
            }
            if(type == POPULAR){
                viewLifecycleOwner.lifecycleScope.launch {
                    seeAllPagesViewModel.popularMovie.collectLatest { resourcePagingData->
                        moviePagingAdapter.submitData(resourcePagingData)
                    }
                }
            }


            with(binding.rvSeeAllFragment){
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = moviePagingAdapter
            }

        }
        if(index==2){
            val tvSeriesPagingAdapter = TvSeriesPagingAdapter()

            binding.rvSeeAllFragment.adapter = tvSeriesPagingAdapter.withLoadStateHeaderAndFooter(
                footer = LoadingStateAdapter{
                    tvSeriesPagingAdapter.retry()
                },
                header = LoadingStateAdapter{
                    tvSeriesPagingAdapter.retry()
                }
            )

            if(type == DISCOVERY){
                viewLifecycleOwner.lifecycleScope.launch {
                    seeAllPagesViewModel.discoverTvSeries.collectLatest { resourcePagingData->
                        tvSeriesPagingAdapter.submitData(resourcePagingData)
                    }
                }
            }
            if(type == POPULAR){
                viewLifecycleOwner.lifecycleScope.launch{
                    seeAllPagesViewModel.popularTvShow.collectLatest { resourcePagingData->
                        tvSeriesPagingAdapter.submitData(resourcePagingData)
                    }
                }
            }


            with(binding.rvSeeAllFragment){
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = tvSeriesPagingAdapter
            }

        }




    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        const val ARG_TYPE = "type_of_list"
        const val DISCOVERY= "Discovery"
        const val POPULAR = "Popular"
        const val UPCOMING = "Up Coming"
    }


}
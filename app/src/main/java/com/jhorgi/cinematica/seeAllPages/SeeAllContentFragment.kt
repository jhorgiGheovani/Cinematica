package com.jhorgi.cinematica.seeAllPages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhorgi.cinematica.common.movieSeeAllAdapter.LoadingStateAdapter
import com.jhorgi.cinematica.common.movieSeeAllAdapter.MoviePagingAdapter
import com.jhorgi.cinematica.common.tvSeriesSeeAllAdapter.TvSeriesPagingAdapter
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

            viewLifecycleOwner.lifecycleScope.launch {
                seeAllPagesViewModel.discoverMovie.collectLatest { resourcePagingData->
                    moviePagingAdapter.submitData(resourcePagingData)
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

            viewLifecycleOwner.lifecycleScope.launch {
                seeAllPagesViewModel.discoverTvSeries.collectLatest { resourcePagingData->
                    tvSeriesPagingAdapter.submitData(resourcePagingData)
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
    }


}
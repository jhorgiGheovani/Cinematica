package com.jhorgi.cinematica.seeAllPages

import android.content.Intent
import android.os.Bundle
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
import com.jhorgi.cinematica.details.DetailsActivity
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


        if(index==1){
            val moviePagingAdapter = MoviePagingAdapter()
            moviePagingAdapter.onItemClick = {data->
                val intent = Intent(activity, DetailsActivity::class.java)
                intent.putExtra(DetailsActivity.EXTRA_DATA, data.movieId)
                intent.putExtra(DetailsActivity.TYPE_DATA, DetailsActivity.MOVIE_TYPE)
                startActivity(intent)
            }
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
                lifecycleScope.launch {
                    seeAllPagesViewModel.popularMovie.collectLatest { resources->
                        moviePagingAdapter.submitData(resources)
                    }
                }

            }

            if(type == UPCOMING){
                viewLifecycleOwner.lifecycleScope.launch {
                    seeAllPagesViewModel.upcomingMovie.collectLatest { resourcePagingData->
                        moviePagingAdapter.submitData(resourcePagingData)
                    }
                }
            }

            if(type == TOP_RATED){
                viewLifecycleOwner.lifecycleScope.launch {
                    seeAllPagesViewModel.topRatedMovie.collectLatest { resourcePagingData->
                        moviePagingAdapter.submitData(resourcePagingData)
                    }
                }
            }

            if(type == NOW_PLAYING_MOVIE){
                viewLifecycleOwner.lifecycleScope.launch {
                    seeAllPagesViewModel.getNowPlayingMovie.collectLatest { resourcePagingData->
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

            tvSeriesPagingAdapter.onItemClick = {data->
                val intent = Intent(activity, DetailsActivity::class.java)
                intent.putExtra(DetailsActivity.EXTRA_DATA, data.id)
                intent.putExtra(DetailsActivity.TYPE_DATA, DetailsActivity.TV_SERIES_TYPE)
                startActivity(intent)
            }

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
            if(type == TOP_RATED){
                viewLifecycleOwner.lifecycleScope.launch{
                    seeAllPagesViewModel.topRatedTvSeries.collectLatest { resourcePagingData->
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
        const val UPCOMING = "Up Coming Movie"
        const val TOP_RATED = "Top Rated"
        const val NOW_PLAYING_MOVIE = "Now Playing Movie"
    }


}
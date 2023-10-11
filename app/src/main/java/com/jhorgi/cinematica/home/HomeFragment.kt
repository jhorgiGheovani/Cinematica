package com.jhorgi.cinematica.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhorgi.cinematica.commonAdapter.movieListAdapterV1.MovieListAdapterV1
import com.jhorgi.cinematica.commonAdapter.tvSeriesListAdapterV1.TvSeriesListAdapterV1
import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.databinding.FragmentHomeBinding
import com.jhorgi.cinematica.details.DetailsActivity
import com.jhorgi.cinematica.home.imagesSliderAdapter.ImagesSliideAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val autoScrollHandler = Handler(Looper.getMainLooper())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        homeViewModel.getPopularMovie()

        fetchPopularMovie()

        homeViewModel.getPopularTvShow()
        fetchPopularTvShow()

        homeViewModel.getUpComingMovie()
        fetchUpComingMovie()

    }

    private fun fetchUpComingMovie() {
        homeViewModel.upComingMovie.observe(viewLifecycleOwner) { upComingMovie ->
            when (upComingMovie) {
                is Resource.Success -> {
                    val imageSlideAdapter =
                        ImagesSliideAdapter(requireContext(), upComingMovie.data)
                    binding.viewPager.adapter = imageSlideAdapter
                    binding.indicator.setViewPager(binding.viewPager)

                    val autoScrollInterval = TimeUnit.SECONDS.toMillis(4)
                    val autoScrollRunnable = object : Runnable {
                        override fun run() {
                            // Change the current item of the ViewPager2
                            val currentItem = binding.viewPager.currentItem
                            val nextItem = if (currentItem < imageSlideAdapter.count - 1) {
                                currentItem + 1
                            } else {
                                0
                            }
                            binding.viewPager.currentItem = nextItem

                            // Schedule the next auto-scroll
                            autoScrollHandler.postDelayed(this, autoScrollInterval)
                        }
                    }
                    autoScrollHandler.postDelayed(autoScrollRunnable, autoScrollInterval)


                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), upComingMovie.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun fetchPopularMovie() {
        val popularMovieLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularMovie.layoutManager = popularMovieLayoutManager

        homeViewModel.popularMovie.observe(viewLifecycleOwner) { popularMovie ->
            when (popularMovie) {
                is Resource.Success -> {
                    val popularMovieAdapter = MovieListAdapterV1(popularMovie.data) { onclick ->
                        val intent = Intent(activity, DetailsActivity::class.java)
                        intent.putExtra(DetailsActivity.EXTRA_DATA, onclick.movieId)
                        intent.putExtra(DetailsActivity.TYPE_DATA, DetailsActivity.MOVIE_TYPE)
                        startActivity(intent)
                    }


                    binding.rvPopularMovie.adapter = popularMovieAdapter
                }

                is Resource.Error -> {
                    Toast.makeText(context, popularMovie.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun fetchPopularTvShow() {
        val popularTvShowsLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularTvShow.layoutManager = popularTvShowsLayoutManager

        homeViewModel.popularTvShow.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val popularMovieAdapter = TvSeriesListAdapterV1(it.data) { onclick ->
                        val intent = Intent(activity, DetailsActivity::class.java)
                        intent.putExtra(DetailsActivity.EXTRA_DATA, onclick.id)
                        intent.putExtra(DetailsActivity.TYPE_DATA, DetailsActivity.TV_SERIES_TYPE)
                        startActivity(intent)
                    }
                    binding.rvPopularTvShow.adapter = popularMovieAdapter


                }

                is Resource.Error -> {}
            }
        }
    }


}
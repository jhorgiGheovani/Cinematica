package com.jhorgi.cinematica.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jhorgi.cinematica.commonAdapter.listAdapterV1.ListAdapterV1
import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.core.utils.DataMapper
import com.jhorgi.cinematica.databinding.FragmentHomeBinding
import com.jhorgi.cinematica.details.DetailsActivity
import com.jhorgi.cinematica.home.imagesSliderAdapter.ImagesSliideAdapter
import com.jhorgi.cinematica.seeAllPages.SeeAllActivity
import com.jhorgi.cinematica.seeAllPages.SeeAllActivity.Companion.TYPE_TITTLE_DATA
import com.jhorgi.cinematica.seeAllPages.SeeAllContentFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var loadedSections = 0

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

        homeViewModel.getTopRatedMovie()
        fetchTopRatedMovie()

        homeViewModel.getTopRatedTvShows()
        fetchTopRatedTvShow()

        homeViewModel.getNowPlayingMovie()
        fetchNowPlayingMovie()

        binding.progressBar.setAnimation("loadingSpinner.lottie")
        binding.progressBar.playAnimation()
        binding.scrollView.setOnTouchListener { _, _ -> true }
        binding.seeAllPopularContent.setOnClickListener {
            val intent = Intent(activity, SeeAllActivity::class.java)
            intent.putExtra(TYPE_TITTLE_DATA, SeeAllContentFragment.POPULAR)
            startActivity(intent)
        }

        binding.seeAllUpcomingMovie.setOnClickListener {
            val intent = Intent(activity, SeeAllActivity::class.java)
            intent.putExtra(TYPE_TITTLE_DATA, SeeAllContentFragment.UPCOMING)
            startActivity(intent)
        }

        binding.seeAllNowPlayingContent.setOnClickListener {
            val intent = Intent(activity, SeeAllActivity::class.java)
            intent.putExtra(TYPE_TITTLE_DATA, SeeAllContentFragment.NOW_PLAYING_MOVIE)
            startActivity(intent)
        }

        binding.seeAllTopRatedContent.setOnClickListener {
            val intent = Intent(activity, SeeAllActivity::class.java)
            intent.putExtra(TYPE_TITTLE_DATA , SeeAllContentFragment.TOP_RATED)
            startActivity(intent)
        }

    }

    private fun updateGlobalLoading() {
        if (loadedSections >= 5) {

            binding.loadingLayout.visibility = View.GONE
            binding.scrollView.setOnTouchListener { _, _ -> false }
        }
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
                    view?.let { it1 ->
                        Snackbar.make(
                            it1,
                            upComingMovie.error,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
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

                    val data = DataMapper.mapMovieToRecyclerViewDataList1(popularMovie.data)
                    val popularMovieAdapter = ListAdapterV1(data) { onclick ->
                        val intent = Intent(activity, DetailsActivity::class.java)
                        intent.putExtra(DetailsActivity.EXTRA_DATA, onclick.id)
                        intent.putExtra(DetailsActivity.TYPE_DATA, DetailsActivity.MOVIE_TYPE)
                        intent.putExtra(DetailsActivity.RATING, onclick.rating)
                        startActivity(intent)
                    }


                    binding.rvPopularMovie.adapter = popularMovieAdapter
                    loadedSections++
                    updateGlobalLoading()
                }

                is Resource.Error -> {
                    view?.let { it1 ->
                        Snackbar.make(it1, popularMovie.error, Snackbar.LENGTH_SHORT).show()
                    }
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
                    val tvShow = DataMapper.mapTvSeriesToRecyclerViewDataList1(it.data)
                    val popularMovieAdapter = ListAdapterV1(tvShow) { onclick ->
                        val intent = Intent(activity, DetailsActivity::class.java)
                        intent.putExtra(DetailsActivity.EXTRA_DATA, onclick.id)
                        intent.putExtra(DetailsActivity.TYPE_DATA, DetailsActivity.TV_SERIES_TYPE)
                        intent.putExtra(DetailsActivity.RATING, onclick.rating)
                        startActivity(intent)
                    }
                    binding.rvPopularTvShow.adapter = popularMovieAdapter
                    loadedSections++
                    updateGlobalLoading()

                }

                is Resource.Error -> {
                    view?.let { it1 -> Snackbar.make(it1, it.error, Snackbar.LENGTH_SHORT).show() }
                }
            }
        }
    }

    private fun fetchTopRatedMovie() {
        val topRatedMovieLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTopRatedMovie.layoutManager = topRatedMovieLayoutManager

        homeViewModel.topRatedMovie.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {

                    val data = DataMapper.mapMovieToRecyclerViewDataList1(it.data)
                    val topRatedMovieAdapter = ListAdapterV1(data) { onclick ->
                        val intent = Intent(activity, DetailsActivity::class.java)
                        intent.putExtra(DetailsActivity.EXTRA_DATA, onclick.id)
                        intent.putExtra(DetailsActivity.TYPE_DATA, DetailsActivity.MOVIE_TYPE)
                        intent.putExtra(DetailsActivity.RATING, onclick.rating)
                        startActivity(intent)
                    }


                    binding.rvTopRatedMovie.adapter = topRatedMovieAdapter
                    loadedSections++
                    updateGlobalLoading()
                }

                is Resource.Error -> {
                    view?.let { it1 -> Snackbar.make(it1, it.error, Snackbar.LENGTH_SHORT).show() }
                }
            }
        }

    }


    private fun fetchTopRatedTvShow(){
        val topRatedTvShowsLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTopRatedTvShow.layoutManager = topRatedTvShowsLayoutManager

        homeViewModel.topRatedTvShow.observe(viewLifecycleOwner){
            when(it) {
                is Resource.Success -> {


                    val tvShow = DataMapper.mapTvSeriesToRecyclerViewDataList1(it.data)
                    val topRatedMovieAdapter = ListAdapterV1(tvShow) { onclick ->
                        val intent = Intent(activity, DetailsActivity::class.java)
                        intent.putExtra(DetailsActivity.EXTRA_DATA, onclick.id)
                        intent.putExtra(DetailsActivity.TYPE_DATA, DetailsActivity.TV_SERIES_TYPE)
                        intent.putExtra(DetailsActivity.RATING, onclick.rating)
                        startActivity(intent)
                    }
                    binding.rvTopRatedTvShow.adapter = topRatedMovieAdapter

                    loadedSections++
                    updateGlobalLoading()

                }
                is Resource.Error -> {
                    view?.let { it1 -> Snackbar.make(it1, it.error, Snackbar.LENGTH_SHORT).show() }
                }
            }
        }
    }

    private fun fetchNowPlayingMovie() {
        val nowPlayingMovieLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvNowPlayingMovie.layoutManager = nowPlayingMovieLayoutManager

        homeViewModel.nowPlayingMovie.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {


                    val data = DataMapper.mapMovieToRecyclerViewDataList1(it.data)
                    val nowPlayingMovieAdapter = ListAdapterV1(data) { onclick ->
                        val intent = Intent(activity, DetailsActivity::class.java)
                        intent.putExtra(DetailsActivity.EXTRA_DATA, onclick.id)
                        intent.putExtra(DetailsActivity.TYPE_DATA, DetailsActivity.MOVIE_TYPE)
                        intent.putExtra(DetailsActivity.RATING, onclick.rating)
                        startActivity(intent)
                    }


                    binding.rvNowPlayingMovie.adapter = nowPlayingMovieAdapter

                    loadedSections++
                    updateGlobalLoading()
                }
                is Resource.Error -> {
                    view?.let { it1 ->
                        Snackbar.make(it1, it.error, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
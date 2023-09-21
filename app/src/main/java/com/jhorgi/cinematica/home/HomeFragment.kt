package com.jhorgi.cinematica.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.jhorgi.cinematica.common.movieSeeAllAdapter.LoadingStateAdapter
import com.jhorgi.cinematica.common.movieSeeAllAdapter.MoviePagingAdapter
import com.jhorgi.cinematica.databinding.FragmentHomeBinding
import com.jhorgi.cinematica.details.DetailsActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity!=null){
            val moviePagingAdapter = MoviePagingAdapter()

            binding.rvMovie.adapter = moviePagingAdapter.withLoadStateHeaderAndFooter (
                footer = LoadingStateAdapter{
                    moviePagingAdapter.retry()
                },
                header = LoadingStateAdapter{
                    moviePagingAdapter.retry()
                }
            )

            //click item
            moviePagingAdapter.onItemClick = {selectedData ->
                val intent = Intent(activity,DetailsActivity::class.java)
                intent.putExtra(DetailsActivity.EXTRA_DATA, selectedData.movieId)
                startActivity(intent)
            }


            viewLifecycleOwner.lifecycleScope.launch {
                homeViewModel.movieWithPaging.collectLatest { resourcePagingData ->

                    moviePagingAdapter.submitData(resourcePagingData)

                }
            }
            with(binding.rvMovie){
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                adapter= moviePagingAdapter
            }


        }
    }


}
package com.jhorgi.cinematica.details

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.imageLoader
import coil.request.ImageRequest
import com.jhorgi.cinematica.R
import com.jhorgi.cinematica.databinding.ActivityDetailsBinding
import com.jhorgi.cinematica.details.adapter.CreditsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private val detailMovieViewModel: DetailsViewModel by viewModel()
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val movieId = intent.getIntExtra(EXTRA_DATA, 0)

        val layoutManagerCast = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        binding.contentLayout.rvCast.layoutManager = layoutManagerCast

        val layoutManagerCrew = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        binding.contentLayout.rvCrew.layoutManager = layoutManagerCrew

        detailMovieViewModel.getMovieCast(movieId)
        detailMovieViewModel.getMovieCrew(movieId)
        detailMovieViewModel.getMovieDetails(movieId)


        detailMovieViewModel.castData.observe(this) { castData ->
            Log.d("Debug Cast", castData.toString())
            val adapter = CreditsAdapter(castData)
            binding.contentLayout.rvCast.adapter = adapter
        }

        detailMovieViewModel.crewItem.observe(this) { crewData ->
            Log.d("Debug Crew", crewData.toString())
            val adapter = CreditsAdapter(crewData)
            binding.contentLayout.rvCrew.adapter = adapter
        }

        detailMovieViewModel.movieDetails.observe(this) { movieDetails ->

            //Background poster
            val request = ImageRequest.Builder(this)
                .data("https://image.tmdb.org/t/p/w300/${movieDetails.posterPath}")
                .target {
                    binding.posterBackground.setImageDrawable(it)
                }
                .build()
            imageLoader.enqueue(request)

            //avatar poster
                val avatarRequest = ImageRequest.Builder(this)
                    .data("https://image.tmdb.org/t/p/w500/${movieDetails.posterPath}")
                    .target {
                        binding.contentLayout.poster.setImageDrawable(it)
                    }
                    .build()
                imageLoader.enqueue(avatarRequest)


            binding.contentLayout.tittleTV.text = movieDetails.title
            binding.contentLayout.overViewContent.text = movieDetails.overview
            binding.contentLayout.years.text = movieDetails.releaseDate.split("-")[0]
            binding.contentLayout.runTimeTV.text = getString(R.string.minutes_property,movieDetails.runtime.toString())

            val genre = movieDetails.genres.map {it.name }

            binding.contentLayout.genres.text = genre.joinToString(", ")


        }


    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
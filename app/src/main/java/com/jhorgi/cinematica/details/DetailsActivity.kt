package com.jhorgi.cinematica.details

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import coil.imageLoader
import coil.request.ImageRequest
import com.google.android.material.snackbar.Snackbar
import com.jhorgi.cinematica.R
import com.jhorgi.cinematica.core.domain.model.FavoriteItem
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.domain.model.TvSeriesDetails
import com.jhorgi.cinematica.core.utils.DataMapper
import com.jhorgi.cinematica.databinding.ActivityDetailsBinding
import com.jhorgi.cinematica.details.adapter.CreditsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.roundToLong

class DetailsActivity : AppCompatActivity() {

    private val detailMovieViewModel: DetailsViewModel by viewModel()
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window?.statusBarColor = ContextCompat.getColor(this, R.color.main_background)
        val movieId = intent.getIntExtra(EXTRA_DATA, 0)
        val seriesId = intent.getIntExtra(EXTRA_DATA, 0)
        val typeData = intent.getStringExtra(TYPE_DATA)
        val ratingData = intent.getStringExtra(RATING)

        val layoutManagerCast = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.discoverContentLayout.rvCast.layoutManager = layoutManagerCast

        val layoutManagerCrew = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.discoverContentLayout.rvCrew.layoutManager = layoutManagerCrew


        binding.discoverContentLayout.backButton.setOnClickListener {
            finish()
        }
        detailMovieViewModel.isFavoriteItem.observe(this) {
            if (it != true) {
                binding.discoverContentLayout.favoriteButton.setColorFilter(getColor(R.color.white))
            }
        }


        if (typeData == MOVIE_TYPE) {
            detailMovieViewModel.isFavoriteItem(movieId)
            detailMovieViewModel.getMovieCast(movieId)
            detailMovieViewModel.getMovieCrew(movieId)
            detailMovieViewModel.getMovieDetails(movieId)

            detailMovieViewModel.castMovieData.observe(this) { castData ->
                val adapter = CreditsAdapter(castData)
                binding.discoverContentLayout.rvCast.adapter = adapter
            }

            detailMovieViewModel.crewMovieData.observe(this) { crewData ->
                val adapter = CreditsAdapter(crewData)
                binding.discoverContentLayout.rvCrew.adapter = adapter
            }

            detailMovieViewModel.movieDetails.observe(this) { movieDetails ->
                bindMovieDetails(movieDetails)

                //Add movie To Favorite
                val data = DataMapper.mapMovieDetailsToFavoriteItem(movieDetails,ratingData.toString())
                addAndDeleteFavoriteItem(movieDetails.id, data, typeData, ratingData.toString())
            }
        }
        if (typeData == TV_SERIES_TYPE) {
            detailMovieViewModel.isFavoriteItem(seriesId)
            detailMovieViewModel.getTvSeriesDetails(seriesId)
            detailMovieViewModel.getTvCast(seriesId)
            detailMovieViewModel.getTvCrew(seriesId)

            detailMovieViewModel.castTvData.observe(this) { castData ->
                val adapter = CreditsAdapter(castData)
                binding.discoverContentLayout.rvCast.adapter = adapter
            }

            detailMovieViewModel.crewTvData.observe(this) { crewData ->
                val adapter = CreditsAdapter(crewData)
                binding.discoverContentLayout.rvCrew.adapter = adapter
            }

            detailMovieViewModel.tvSeriesDetails.observe(this) { tvSeriesDetails ->
                bindTvSeriesData(tvSeriesDetails)

                //Add tv series to Favorite
                val data = DataMapper.mapTvDetailsToFavoriteItem(tvSeriesDetails, ratingData.toString())
                addAndDeleteFavoriteItem(tvSeriesDetails.id, data, typeData, ratingData.toString())
            }
        }

    }

    private fun bindMovieDetails(data: MovieDetails) {
        //Load Images
        val request = ImageRequest.Builder(this)
            .data("https://image.tmdb.org/t/p/w300/${data.posterPath}")
            .target {
                binding.posterBackground.setImageDrawable(it)
                binding.discoverContentLayout.poster.setImageDrawable(it)
            }
            .build()
        imageLoader.enqueue(request)
        //bind tittle
        binding.discoverContentLayout.tittleTV.text = data.title

        //bind overview
        binding.discoverContentLayout.overViewContent.text = data.overview


        //bind release years
        binding.discoverContentLayout.years.text = data.releaseDate.split("-")[0]

        //bind runtime
        binding.discoverContentLayout.runTimeTV.text =
            getString(R.string.minutes_property, data.runtime.toString())

        //bind genres
        val genre = data.genres?.map { it.name }
        binding.discoverContentLayout.genres.text = genre?.joinToString(", ")
        val roundedValue = (data.voteAverage.times(10)).roundToLong() / 10.0
        binding.discoverContentLayout.textStart.text = roundedValue.toString()
    }

    private fun bindTvSeriesData(data: TvSeriesDetails) {
        //Load Images
        val request = ImageRequest.Builder(this)
            .data("https://image.tmdb.org/t/p/w300/${data.posterPath}")
            .target {
                binding.posterBackground.setImageDrawable(it)
                binding.discoverContentLayout.poster.setImageDrawable(it)
            }
            .build()
        imageLoader.enqueue(request)

        //bind tittle
        binding.discoverContentLayout.tittleTV.text = data.title

        //bind overview
        if (data.overview != "") {
            binding.discoverContentLayout.overViewContent.text = data.overview
        }

        //bind release years
        if (data.releaseDate != null) {
            binding.discoverContentLayout.years.text = data.releaseDate!!.split("-")[0]
        }


        //bind runtime
        binding.discoverContentLayout.runTimeTV.text =
            getString(R.string.no_runtime)

        //bind genres
        val genre = data.genres?.map { it.name }
        Log.d("Genre", genre.toString())
        binding.discoverContentLayout.genres.text = genre?.joinToString(", ")
        val roundedValue = (data.voteAverage?.times(10))!!.roundToLong() / 10.0
        binding.discoverContentLayout.textStart.text = roundedValue.toString()
    }

    private fun addAndDeleteFavoriteItem(id: Int?, data: FavoriteItem, category: String, rating: String) {
        binding.discoverContentLayout.favoriteButton.setOnClickListener {
            val currentTimeMillis = System.currentTimeMillis()  //Time Stamp
            //check favorite status by love button color
            if (binding.discoverContentLayout.favoriteButton.colorFilter == null) {
                if (id != null) {
                    detailMovieViewModel.deleteMovieFromFavorite(id)
                    Snackbar.make(binding.root,"This item has been deleted from your favorite.", Snackbar.LENGTH_SHORT).show()
                }
                binding.discoverContentLayout.favoriteButton.setColorFilter(getColor(R.color.white))
            } else {
                binding.discoverContentLayout.favoriteButton.colorFilter = null
                detailMovieViewModel.addMovieToFavorite(data, currentTimeMillis, category, rating)
                Snackbar.make(binding.root,"This item has been added to your favorite.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val TYPE_DATA = "type_data"
        const val MOVIE_TYPE = "movie"
        const val TV_SERIES_TYPE = "tv_series"
        const val RATING ="rating"
    }
}
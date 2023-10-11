package com.jhorgi.cinematica.core.domain.usecase

import androidx.paging.PagingData
import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.FavoriteItem
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.core.domain.model.TvSeriesDetails
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getPopularMovie(): Flow<Resource<List<Movie>>>

    fun getTopRatedMovie(): Flow<Resource<List<Movie>>>

    fun getNowPlayingMovie(): Flow<Resource<List<Movie>>>

    fun getPopularTvShow(): Flow<Resource<List<TvSeries>>>

    fun getTopRatedTvShows(): Flow<Resource<List<TvSeries>>>
    fun movieSearch(query:String): Flow<Resource<List<Movie>>>

    fun getUpComingMovie(): Flow<Resource<List<Movie>>>
    fun discoverMovie(): Flow<Resource<List<Movie>>>

    fun getDiscoverMovieWithPaging(): Flow<PagingData<Movie>>

    fun discoverTvShow(): Flow<Resource<List<TvSeries>>>

    fun getDiscoverTvSeriesWithPaging(): Flow<PagingData<TvSeries>>
    fun getMovieDetails(movieId: Int) : Flow<MovieDetails>

    fun getTvSeriesDetails(seriesId: Int) : Flow<TvSeriesDetails>
    fun getMovieCast(movieId: Int) : Flow<List<Credit>>

    fun getMovieCrew(movieId: Int): Flow<List<Credit>>

    fun getTvCast(seriesId: Int): Flow<List<Credit>>

    fun getTvCrew(seriesId: Int): Flow<List<Credit>>

    suspend fun addItemToFavorite(movie: FavoriteItem, timeStamp: Long, category: String)

    suspend fun deleteMovieFromFavorite(movieId: Int)

    fun getListOfFavoriteItem(category: String): Flow<List<FavoriteItem>>

    fun isFavoriteItem(movieId: Int): Flow<Boolean>
}
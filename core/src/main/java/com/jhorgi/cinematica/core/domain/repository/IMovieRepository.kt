package com.jhorgi.cinematica.core.domain.repository

import androidx.paging.PagingData
import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.FavoriteItem
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.core.domain.model.TvSeriesDetails
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getPopularMovie(): Flow<Resource<List<Movie>>>

    fun getPopularMovieWithPaging(): Flow<PagingData<Movie>>

    fun getTopRatedMovie(): Flow<Resource<List<Movie>>>

    fun getTopRatedMovieWithPaging(): Flow<PagingData<Movie>>
    fun getNowPlayingMovie(): Flow<Resource<List<Movie>>>

    fun getNowPlayingMovieWithPaging(): Flow<PagingData<Movie>>

    fun getPopularTvShow(): Flow<Resource<List<TvSeries>>>

    fun getPopularTvShowWithPaging(): Flow<PagingData<TvSeries>>

    fun getTopRatedTvShows(): Flow<Resource<List<TvSeries>>>

    fun getTopRatedTvShowsWithPaging(): Flow<PagingData<TvSeries>>
    fun movieSearch(query:String): Flow<Resource<List<Movie>>>

    fun tvSeriesSearch(query:String): Flow<Resource<List<TvSeries>>>

    fun getUpComingMovie(): Flow<Resource<List<Movie>>>

    fun getUpComingMovieWithPaging(): Flow<PagingData<Movie>>

    fun discoverMovie(): Flow<Resource<List<Movie>>>

    fun getDiscoverMovieWithPaging(): Flow<PagingData<Movie>>

    fun discoverTvShow(): Flow<Resource<List<TvSeries>>>

    fun getDiscoverTvSeriesWithPaging(): Flow<PagingData<TvSeries>>

    fun getMovieDetails(movie_id: Int): Flow<MovieDetails>

    fun getTvSeriesDetails(series_id: Int): Flow<TvSeriesDetails>
    fun getMovieCast(movie_id: Int): Flow<List<Credit>>

    fun getMovieCrew(movie_id: Int): Flow<List<Credit>>

    fun getTvCast(series_id: Int): Flow<List<Credit>>

    fun getTvCrew(series_id: Int): Flow<List<Credit>>

    suspend fun addMovieToFavorite(movie: FavoriteItem, timeStamp: Long, category: String, rating: String)

    suspend fun deleteMovieFromFavorite(itemId: Int)

    fun getListOfFavoriteItem(category: String): Flow<List<FavoriteItem>>

    fun isFavoriteItem(movie_id: Int): Flow<Boolean>
}
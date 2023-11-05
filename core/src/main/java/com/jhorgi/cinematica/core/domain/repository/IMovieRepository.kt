package com.jhorgi.cinematica.core.domain.repository

import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.core.data.pagingDataSource.MoviePagingSource
import com.jhorgi.cinematica.core.data.pagingDataSource.TvSeriesPagingSource
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.FavoriteItem
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.core.domain.model.TvSeriesDetails
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getPopularMovie(): Flow<Resource<List<Movie>>>


    fun moviePagingSource(argument: String): MoviePagingSource

    fun tvSeriesPagingSource(argument: String): TvSeriesPagingSource

    fun getTopRatedMovie(): Flow<Resource<List<Movie>>>

    fun getNowPlayingMovie(): Flow<Resource<List<Movie>>>


    fun getPopularTvShow(): Flow<Resource<List<TvSeries>>>


    fun getTopRatedTvShows(): Flow<Resource<List<TvSeries>>>

    fun movieSearch(query:String): Flow<Resource<List<Movie>>>

    fun tvSeriesSearch(query:String): Flow<Resource<List<TvSeries>>>

    fun getUpComingMovie(): Flow<Resource<List<Movie>>>


    fun discoverMovie(): Flow<Resource<List<Movie>>>


    fun discoverTvShow(): Flow<Resource<List<TvSeries>>>


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
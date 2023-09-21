package com.jhorgi.cinematica.core.domain.usecase

import androidx.paging.PagingData
import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.FavoriteItem
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.domain.model.TvSeriesDetails
import com.jhorgi.cinematica.core.domain.model.TvSeries
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovie(): Flow<PagingData<Movie>>

    fun discoverMovie(): Flow<Resource<List<Movie>>>

    fun getDiscoverMovieWithPaging(): Flow<PagingData<Movie>>

    fun discoverTvShow(): Flow<Resource<List<TvSeries>>>

    fun getDiscoverTvSeriesWithPaging(): Flow<PagingData<TvSeries>>
    fun getMovieDetails(movie_id: Int) : Flow<MovieDetails>

    fun getTvSeriesDetails(series_id: Int) : Flow<TvSeriesDetails>
    fun getMovieCast(movie_id: Int) : Flow<List<Credit>>

    fun getMovieCrew(movie_id: Int): Flow<List<Credit>>

    fun getTvCast(series_id: Int): Flow<List<Credit>>

    fun getTvCrew(series_id: Int): Flow<List<Credit>>

    suspend fun addItemToFavorite(movie: FavoriteItem, timeStamp: Long, category: String)

    suspend fun deleteMovieFromFavorite(movie_id: Int)

    fun getListOfFavoriteItem(category: String): Flow<List<FavoriteItem>>

    fun isFavoriteItem(movieId: Int): Flow<Boolean>
}
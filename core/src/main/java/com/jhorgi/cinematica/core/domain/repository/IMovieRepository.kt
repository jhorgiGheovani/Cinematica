package com.jhorgi.cinematica.core.domain.repository

import androidx.paging.PagingData
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.FavoriteMovie
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovie(): Flow<PagingData<Movie>>

    fun getMovieDetails(movie_id: Int): Flow<MovieDetails>
    fun getCast(movie_id: Int): Flow<List<Credit>>

    fun getCrew(movie_id: Int): Flow<List<Credit>>

    suspend fun addMovieToFavorite(movie: MovieDetails, timeStamp: Long)

    suspend fun deleteMovieFromFavorite(itemId: Int)

    fun getFavoriteMovie(): Flow<List<FavoriteMovie>>

    fun isFavoriteMovie(movie_id: Int): Flow<Boolean>
}
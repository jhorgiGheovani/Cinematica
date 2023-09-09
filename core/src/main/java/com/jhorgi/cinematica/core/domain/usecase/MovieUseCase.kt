package com.jhorgi.cinematica.core.domain.usecase

import androidx.paging.PagingData
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovie(): Flow<PagingData<Movie>>

    fun getMovieDetails(movie_id: Int) : Flow<MovieDetails>
    fun getCast(movie_id: Int) : Flow<List<Credit>>

    fun getCrew(movie_id: Int): Flow<List<Credit>>

}
package com.jhorgi.cinematica.core.domain.usecase

import androidx.paging.PagingData
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.domain.repository.IMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getMovie(): Flow<PagingData<Movie>> =movieRepository.getMovie()
    override fun getMovieDetails(movie_id: Int): Flow<MovieDetails> = movieRepository.getMovieDetails(movie_id)

    override fun getCast(movie_id: Int): Flow<List<Credit>> = movieRepository.getCast(movie_id).flowOn(Dispatchers.IO)
    override fun getCrew(movie_id: Int): Flow<List<Credit>>  = movieRepository.getCrew(movie_id).flowOn(Dispatchers.IO)
}
package com.jhorgi.cinematica.core.domain.usecase

import androidx.paging.PagingData
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getMovie(): Flow<PagingData<Movie>> =movieRepository.getMovie()
}
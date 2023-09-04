package com.jhorgi.cinematica.core.domain.repository

import androidx.paging.PagingData
import com.jhorgi.cinematica.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovie(): Flow<PagingData<Movie>>
}
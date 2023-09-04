package com.jhorgi.cinematica.core.data

import androidx.paging.PagingData
import com.jhorgi.cinematica.core.data.source.remote.RemoteDataSource
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepository(
//    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : IMovieRepository {
    override fun getMovie(): Flow<PagingData<Movie>> = remoteDataSource.getMovie()

}
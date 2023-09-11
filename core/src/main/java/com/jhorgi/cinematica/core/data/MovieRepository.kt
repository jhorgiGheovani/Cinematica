package com.jhorgi.cinematica.core.data

import androidx.paging.PagingData
import com.jhorgi.cinematica.core.data.source.local.LocalDataSource
import com.jhorgi.cinematica.core.data.source.remote.RemoteDataSource
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.FavoriteMovie
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : IMovieRepository {
    override fun getMovie(): Flow<PagingData<Movie>> = remoteDataSource.getMovie()
    override fun getMovieDetails(movie_id: Int): Flow<MovieDetails>  =  remoteDataSource.getMovieDetails(movie_id)

    override fun getCast(movie_id: Int): Flow<List<Credit>> =
        remoteDataSource.getCast(movie_id)

    override fun getCrew(movie_id: Int): Flow<List<Credit>> = remoteDataSource.getCrews(movie_id)
    override suspend fun addMovieToFavorite(movie: MovieDetails, timeStamp: Long) {
        return localDataSource.addMovieToFavorite(movie, timeStamp)
    }

    override suspend fun deleteMovieFromFavorite(itemId: Int) {
        return localDataSource.deleteItemById(itemId)
    }

    override fun getFavoriteMovie(): Flow<List<FavoriteMovie>> = localDataSource.getListOfFavoriteMovie()
    override fun isFavoriteMovie(movie_id: Int): Flow<Boolean>  = localDataSource.isFavoriteMovie(movie_id)

}
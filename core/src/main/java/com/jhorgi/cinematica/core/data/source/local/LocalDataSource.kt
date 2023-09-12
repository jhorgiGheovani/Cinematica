package com.jhorgi.cinematica.core.data.source.local

import android.util.Log
import com.jhorgi.cinematica.core.data.source.local.room.MovieDao
import com.jhorgi.cinematica.core.domain.model.FavoriteMovie
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSource(private val movieDao: MovieDao) {


    suspend fun addMovieToFavorite(movie: MovieDetails, timeStamp: Long) {
        try {
            val movieEntity = DataMapper.mapMovieDetailDomainToMovieEntity(movie, timeStamp)
            movieDao.insertMovie(movieEntity)
        } catch (e: Exception) {
            Log.d("Error when addMovieToFavorite", e.message.toString())
        }

    }

    suspend fun deleteItemById(itemId: Int) = movieDao.deleteById(itemId)

    fun getListOfFavoriteMovie(): Flow<List<FavoriteMovie>> = flow {
        try {
            //Change entity to domain
           movieDao.getListOfFavoriteMovie().collect{
                DataMapper.mapEntityToDomain(it)
                emit(DataMapper.mapEntityToDomain(it)) //emit every cahnges that happen
            }
        } catch (e: Exception) {
            Log.d("Error when getListOfFavoriteMovie", e.message.toString())
        }
    }

    fun isFavoriteMovie(movieId: Int): Flow<Boolean> = flow {
        try {
            emit(movieDao.isFavoriteMovie(movieId))
        } catch (e: Exception) {
            Log.d("Error isFavoriteMovie", e.message.toString())
        }
    }
}
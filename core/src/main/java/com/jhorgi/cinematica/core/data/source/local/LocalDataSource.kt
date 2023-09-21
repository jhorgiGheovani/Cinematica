package com.jhorgi.cinematica.core.data.source.local

import android.util.Log
import com.jhorgi.cinematica.core.data.source.local.room.MovieDao
import com.jhorgi.cinematica.core.domain.model.FavoriteItem
import com.jhorgi.cinematica.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSource(private val movieDao: MovieDao) {


    suspend fun addMovieToFavorite(movie: FavoriteItem, timeStamp: Long, category: String) {
        try {
            val movieEntity = DataMapper.mapDomainToMovieEntity(movie, timeStamp, category)
            movieDao.insertMovie(movieEntity)
        } catch (e: Exception) {
            Log.d("Error when addMovieToFavorite", e.message.toString())
        }

    }

    suspend fun deleteItemById(itemId: Int) = movieDao.deleteById(itemId)

    fun getListOfFavoriteItem(category: String): Flow<List<FavoriteItem>> = flow {
        try {
            //Change entity to domain
           movieDao.getListOfFavoriteItem(category).collect{
                DataMapper.mapEntityToDomain(it)
                emit(DataMapper.mapEntityToDomain(it)) //emit every cahnges that happen
            }
        } catch (e: Exception) {
            Log.d("Error when getListOfFavoriteItem", e.message.toString())
        }
    }

    fun isFavoriteItem(movieId: Int): Flow<Boolean> = flow {
        try {
            emit(movieDao.isFavoriteItem(movieId))
        } catch (e: Exception) {
            Log.d("Error isFavoriteItem", e.message.toString())
        }
    }
}
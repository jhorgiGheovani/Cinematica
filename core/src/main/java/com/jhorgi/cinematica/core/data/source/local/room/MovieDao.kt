package com.jhorgi.cinematica.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhorgi.cinematica.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)
    @Query("DELETE FROM favorite WHERE movieId = :itemId")
    suspend fun deleteById(itemId: Int)

    @Query("SELECT * FROM favorite WHERE category = :category ORDER BY timeStamp DESC")
    fun getListOfFavoriteItem(category: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM favorite WHERE movieId = :itemId")
    fun isFavoriteItem(itemId: Int): Boolean
}
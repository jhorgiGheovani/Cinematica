package com.jhorgi.cinematica.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jhorgi.cinematica.core.data.source.local.entity.MovieEntity
import com.jhorgi.cinematica.core.utils.ConvertersGenre

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(ConvertersGenre::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
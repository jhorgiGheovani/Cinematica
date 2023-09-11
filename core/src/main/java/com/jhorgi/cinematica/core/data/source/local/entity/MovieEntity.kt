package com.jhorgi.cinematica.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jhorgi.cinematica.core.domain.model.GenresItem
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    var movieId: Int,

    @ColumnInfo(name = "tittle")
    var title: String,

    @ColumnInfo(name = "genres")
    var genres: List<GenresItem>,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double,

    @ColumnInfo(name = "timeStamp")
    var timeStamp: Long
) : Parcelable
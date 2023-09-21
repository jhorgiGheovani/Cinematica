package com.jhorgi.cinematica.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val title: String,
    val overview: String,
    val originalLanguage: String,
    val genres: List<Int>,
    val originalTitle: String,
    val video: Boolean,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
) : Parcelable
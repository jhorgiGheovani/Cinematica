package com.jhorgi.cinematica.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val title: String,
    val overview: String,
    val originalLanguage: String,
    val genres: List<String>,
    val originalTitle: String,
    val posterPath: String?=null,
    val backdropPath: String?=null,
    val releaseDate: String,
    val rating: Double
) : Parcelable
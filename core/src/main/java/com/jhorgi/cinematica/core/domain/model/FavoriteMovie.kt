package com.jhorgi.cinematica.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteMovie(
    val id: Int,
    val title: String,
    val genres: List<GenresItem>,
    val releaseDate: String,
    val overview: String,
    val posterPath: String?=null,
    val voteAverage: Double
) : Parcelable
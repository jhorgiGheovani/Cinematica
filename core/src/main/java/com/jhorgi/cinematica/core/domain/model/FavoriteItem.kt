package com.jhorgi.cinematica.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteItem(
    val id: Int? = null,
    val title: String? = null,
    val genres: List<GenresItem>? = null,
    val releaseDate: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val voteAverage: Double? = null
) : Parcelable
package com.jhorgi.cinematica.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvSeriesDetails(
    val id: Int,
    val posterPath: String? = null,
    val title: String,
    val overview: String? = "No overview",
    val genres: List<GenresItem>? = null,
    val originalTitle: String? = null,
    val releaseDate: String? = null,
    val voteAverage: Double? = null,
) : Parcelable
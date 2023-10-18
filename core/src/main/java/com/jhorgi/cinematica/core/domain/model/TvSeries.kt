package com.jhorgi.cinematica.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvSeries(
    val id: Int? = null,
    val title: String? = null,
    val overview: String? = null,
    val originalLanguage: String? = null,
    val genres: List<String>? = null,
    val originalTitle: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val firstAirDate: String? = null,
) : Parcelable
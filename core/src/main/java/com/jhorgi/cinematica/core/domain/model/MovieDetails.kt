package com.jhorgi.cinematica.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieDetails(
        val id: Int,
        val posterPath: String?=null,
        val title: String,
        val overview: String,
        val genres: List<GenresItem>?=null,
        val originalTitle: String,
        val runtime: Int,
        val releaseDate: String,
        val voteAverage: Double,
) : Parcelable


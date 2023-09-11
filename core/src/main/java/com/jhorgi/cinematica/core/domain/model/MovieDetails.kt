package com.jhorgi.cinematica.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieDetails(
        val originalLanguage: String,
        val imdbId: String,
        val video: Boolean,
        val title: String,
        val backdropPath: String,
        val revenue: Int,
        val genres: List<GenresItem>,
        val popularity: Double,
        val id: Int,
        val voteCount: Int,
        val budget: Int,
        val overview: String,
        val originalTitle: String,
        val runtime: Int,
        val posterPath: String?=null,
        val releaseDate: String,
        val voteAverage: Double,
        val tagline: String,
        val adult: Boolean,
        val homepage: String,
        val status: String
) : Parcelable


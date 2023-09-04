package com.jhorgi.cinematica.core.utils

import com.jhorgi.cinematica.core.data.source.remote.response.ResultsItem
import com.jhorgi.cinematica.core.domain.model.Movie

object DataMapper {

    fun mapResponseToDomain(input: ResultsItem) = Movie(
        movieId = input.id,
        title = input.title,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        video = input.video,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate
    )
}
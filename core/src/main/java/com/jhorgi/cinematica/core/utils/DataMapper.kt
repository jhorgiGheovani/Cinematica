package com.jhorgi.cinematica.core.utils

import com.jhorgi.cinematica.core.data.source.remote.response.CreditsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.MovieDetailsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.ResultsItem
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.GenresItem
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.MovieDetails

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

    fun mapMovieDetailsResponseToMovieDetails(
        input: MovieDetailsResponse
    ): MovieDetails {

        val genre = input.genres.map {
            GenresItem(it.name)
        }
        return MovieDetails(
            originalLanguage = input.originalLanguage,
            imdbId = input.imdbId,
            video = input.video,
            title = input.title,
            backdropPath = input.backdropPath,
            revenue = input.revenue,
            genres = genre, // You can't directly map a list to a string without custom logic
            popularity = input.popularity,
            id = input.id,
            voteCount = input.voteCount,
            budget = input.budget,
            overview = input.overview,
            originalTitle = input.originalTitle,
            runtime = input.runtime,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            tagline = input.tagline,
            adult = input.adult,
            homepage = input.homepage,
            status = input.status
        )
    }

    fun mapCreditsResponseToCrewCreditModel(input: CreditsResponse): List<Credit> =
        input.crew.map {
            Credit(
                id = it.id,
                name = it.name,
                positionOrCharacter = it.job,
                imagesPath = it.profilePath
            )
        }

    fun mapCreditsResponseToCastCreditModel(input: CreditsResponse): List<Credit> =
        input.cast.map {
            Credit(
                id = it.id,
                name = it.name,
                positionOrCharacter = it.character,
                imagesPath = it.profilePath
            )
        }

}
package com.jhorgi.cinematica.core.utils

import com.jhorgi.cinematica.core.data.source.local.entity.MovieEntity
import com.jhorgi.cinematica.core.data.source.remote.response.CreditsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.MovieDetailsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.ResultsItem
import com.jhorgi.cinematica.core.data.source.remote.response.TvDetailsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.TvResults
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.FavoriteItem
import com.jhorgi.cinematica.core.domain.model.GenresItem
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.core.domain.model.TvSeriesDetails

object DataMapper {

    fun mapEntityToDomain(input: List<MovieEntity>): List<FavoriteItem> =
        input.map {
            FavoriteItem(
                id = it.movieId,
                title = it.title,
                genres = it.genres,
                releaseDate = it.releaseDate,
                overview = it.overview,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage
            )
        }

    fun mapDomainToMovieEntity(input: FavoriteItem, timeStamp: Long, category: String) =
        MovieEntity(
            movieId = input.id,
            title = input.title,
            genres = input.genres,
            releaseDate = input.releaseDate,
            overview = input.overview,
            posterPath = input.posterPath,
            voteAverage = input.voteAverage,
            timeStamp = timeStamp,
            category = category
        )

    fun mapMovieDetailsToFavoriteItem(input: MovieDetails) = FavoriteItem(
        id = input.id,
        title = input.title,
        genres = input.genres,
        releaseDate = input.releaseDate,
        overview = input.overview,
        posterPath = input.posterPath,
        voteAverage = input.voteAverage,
    )

    fun mapTvDetailsToFavoriteItem(input: TvSeriesDetails) = FavoriteItem(
        id = input.id,
        title = input.title,
        genres = input.genres,
        releaseDate = input.releaseDate,
        overview = input.overview,
        posterPath = input.posterPath,
        voteAverage = input.voteAverage,
    )

    fun mapMovieResponseToDomain(input: ResultsItem, genre: List<String>) = Movie(
        movieId = input.id,
        title = input.title,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        genres = genre
    )

    fun mapTvShowResponseToDomain(input: TvResults, genre: List<String>) = TvSeries(
        id = input.id,
        title = input.name,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalName,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        firstAirDate = input.firstAirDate,
        genres = genre
    )

    fun mapMovieDetailsResponseToMovieDetails(
        input: MovieDetailsResponse
    ): MovieDetails {
//
        val genre = input.genres?.map {
            GenresItem(id = it.id, name = it.name)
        }
        return MovieDetails(
            title = input.title, // You can't directly map a list to a string without custom logic
            id = input.id,
            overview = input.overview,
            originalTitle = input.originalTitle,
            runtime = input.runtime,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            genres = genre
        )
    }

    fun mapTvDetailsResponseToDomain(
        input: TvDetailsResponse
    ): TvSeriesDetails {

        val genre = input.genres?.map {
            GenresItem(id = it.id, name = it.name)
        }

        return TvSeriesDetails(
            title = input.name,
            genres = genre, // You can't directly map a list to a string without custom logic
            id = input.id,
            overview = input.overview,
            originalTitle = input.originalName,
            posterPath = input.posterPath,
            releaseDate = input.firstAirDate,
            voteAverage = input.voteAverage,
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
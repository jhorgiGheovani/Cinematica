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
import com.jhorgi.cinematica.core.domain.model.TvSeriesDetails
import com.jhorgi.cinematica.core.domain.model.TvSeries

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

    fun mapDomainToMovieEntity(input: FavoriteItem, timeStamp: Long, category: String) = MovieEntity(
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
        id=input.id,
        title = input.title,
        genres = input.genres,
        releaseDate = input.releaseDate,
        overview = input.overview,
        posterPath = input.posterPath,
        voteAverage = input.voteAverage,
    )

    fun mapTvDetailsToFavoriteItem(input: TvSeriesDetails)= FavoriteItem(
        id=input.id,
        title = input.title,
        genres = input.genres,
        releaseDate = input.releaseDate,
        overview = input.overview,
        posterPath = input.posterPath,
        voteAverage = input.voteAverage,
    )

    fun mapMovieResponseToDomain(input: ResultsItem) = Movie(
        movieId = input.id,
        title = input.title,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        video = input.video,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        genres = input.genreIds
    )

    fun mapTvShowResponseToDomain(input: TvResults) = TvSeries(
        id = input.id,
        title = input.name,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalName,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        firstAirDate = input.firstAirDate,
        genres = input.genreIds
    )

    fun mapMovieDetailsResponseToMovieDetails(
        input: MovieDetailsResponse
    ): MovieDetails {

        val genre = input.genres?.map {
            GenresItem(it.name)
        }
        return MovieDetails(
            title = input.title,
            genres = genre, // You can't directly map a list to a string without custom logic
            id = input.id,
            overview = input.overview,
            originalTitle = input.originalTitle,
            runtime = input.runtime,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
        )
    }

    fun mapTvDetailsResponseToDomain(
        input: TvDetailsResponse
    ): TvSeriesDetails {

        val genre = input.genres?.map {
            GenresItem(it.name)
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

    fun getMovieGenres(ids: List<Int>): List<String> {
        val genres = mapOf(
            28 to "Action",
            12 to "Adventure",
            16 to "Animation",
            35 to "Comedy",
            80 to "Crime",
            99 to "Documentary",
            18 to "Drama",
            10751 to "Family",
            14 to "Fantasy",
            36 to "History",
            27 to "Horror",
            10402 to "Music",
            9648 to "Mystery",
            10749 to "Romance",
            878 to "Science Fiction",
            10770 to "TV Movie",
            53 to "Thriller",
            10752 to "War",
            37 to "Western"
        )
        return ids.map { genres[it].toString() }
    }


}
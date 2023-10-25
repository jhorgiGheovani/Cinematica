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
import com.jhorgi.cinematica.core.domain.model.RecyclerViewDataList1
import com.jhorgi.cinematica.core.domain.model.RecyclerViewDataList2
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

    fun mapDomainToMovieEntity(input: FavoriteItem, timeStamp: Long, category: String, rating:String) =
        MovieEntity(
            movieId = input.id,
            title = input.title,
            genres = input.genres,
            releaseDate = input.releaseDate,
            overview = input.overview,
            posterPath = input.posterPath,
            voteAverage = rating,
            timeStamp = timeStamp,
            category = category
        )

    fun mapMovieDetailsToFavoriteItem(input: MovieDetails, rating: String) = FavoriteItem(
        id = input.id,
        title = input.title,
        genres = input.genres,
        releaseDate = input.releaseDate,
        overview = input.overview,
        posterPath = input.posterPath,
        voteAverage = rating,
    )

    fun mapTvDetailsToFavoriteItem(input: TvSeriesDetails, rating: String) = FavoriteItem(
        id = input.id,
        title = input.title,
        genres = input.genres,
        releaseDate = input.releaseDate,
        overview = input.overview,
        posterPath = input.posterPath,
        voteAverage = rating,
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
        genres = genre,
        rating = input.voteAverage
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
        genres = genre,
        voteAverage = input.voteAverage
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

    fun mapMovieToRecyclerViewDataList1(input: List<Movie>) =
        input.map {
            RecyclerViewDataList1(
                id = it.movieId,
                tittle = it.title,
                posterPath = it.posterPath,
                genres = it.genres,
                rating = it.rating.toString()
            )
        }

    fun mapTvSeriesToRecyclerViewDataList1(input: List<TvSeries>) =
        input.map {
            RecyclerViewDataList1(
                id = it.id,
                tittle = it.title,
                posterPath = it.posterPath,
                genres = it.genres,
                rating = it.voteAverage.toString()
            )
        }

    fun mapMovieToRecyclerViewDataList2(input: List<Movie>) =
        input.map {
            RecyclerViewDataList2(
                id = it.movieId,
                tittle = it.title,
                posterPath = it.posterPath,
                overview = it.overview,
                releaseDate = it.releaseDate,
                genres = it.genres,
                rating = it.rating.toString()
            )
        }

    fun mapTvSeriesToRecyclerViewDataList2(input: List<TvSeries>) =
        input.map {
            RecyclerViewDataList2(
                id = it.id,
                tittle = it.title,
                posterPath = it.posterPath,
                overview = it.overview!!,
                releaseDate = it.firstAirDate,
                genres = it.genres
            )
        }

    fun mapFavoriteItemToRecyclerViewDataList2(input: List<FavoriteItem>) =
        input.map {
            val genre = it.genres?.map { data -> data.name }
            RecyclerViewDataList2(
                id = it.id,
                tittle = it.title,
                posterPath = it.posterPath,
                overview = it.overview,
                releaseDate = it.releaseDate,
                genres = genre,
                rating = it.voteAverage.toString()
            )

        }

}
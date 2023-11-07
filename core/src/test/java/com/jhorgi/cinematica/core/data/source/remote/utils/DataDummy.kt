package com.jhorgi.cinematica.core.data.source.remote.utils

import com.jhorgi.cinematica.core.data.source.remote.response.BelongsToCollection
import com.jhorgi.cinematica.core.data.source.remote.response.CastItem
import com.jhorgi.cinematica.core.data.source.remote.response.CreditsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.CrewItem
import com.jhorgi.cinematica.core.data.source.remote.response.Dates
import com.jhorgi.cinematica.core.data.source.remote.response.GenresItem
import com.jhorgi.cinematica.core.data.source.remote.response.GenresResponse
import com.jhorgi.cinematica.core.data.source.remote.response.GenresTvItem
import com.jhorgi.cinematica.core.data.source.remote.response.LastEpisodeToAir
import com.jhorgi.cinematica.core.data.source.remote.response.ListMovieResponse
import com.jhorgi.cinematica.core.data.source.remote.response.MovieDetailsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.NetworksItem
import com.jhorgi.cinematica.core.data.source.remote.response.ProductionCompaniesItem
import com.jhorgi.cinematica.core.data.source.remote.response.ProductionCountriesItem
import com.jhorgi.cinematica.core.data.source.remote.response.ProductionCountriesTvItem
import com.jhorgi.cinematica.core.data.source.remote.response.ResultsItem
import com.jhorgi.cinematica.core.data.source.remote.response.SeasonsItem
import com.jhorgi.cinematica.core.data.source.remote.response.SpokenLanguagesItem
import com.jhorgi.cinematica.core.data.source.remote.response.SpokenLanguagesTvItem
import com.jhorgi.cinematica.core.data.source.remote.response.TvDetailsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.TvListResponse
import com.jhorgi.cinematica.core.data.source.remote.response.TvResults
import com.jhorgi.cinematica.core.data.source.remote.response.UpcomingmovieResponse
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.TvSeries

object DataDummy {

    fun generateListMovieResponse(): ListMovieResponse {

        val listResultItem = ArrayList<ResultsItem>()
        for (i in 0..10) {
            val resultItem = ResultsItem(
                overview = "overview $i",
                originalLanguage = "lang $i",
                originalTitle = "original tittle ${i + 1}",
                video = false,
                title = "Movie ${i + 1}",
                genreIds = listOf(1, 2, 3),
                posterPath = "posterPath $i",
                backdropPath = "backdropPath $i",
                releaseDate = "releaseDate $i",
                popularity = 3.43,
                voteAverage = 3.2699,
                id = i,
                adult = true,
                voteCount = i
            )
            listResultItem.add(resultItem)
        }

        return ListMovieResponse(
            page = 1,
            totalPages = 5,
            results = listResultItem,
            totalResults = 12
        )
    }


    fun dummyListTvSeriesData(): List<TvSeries>{
        val dummyTvSeriesData = ArrayList<TvSeries>()

        for(i in 0..5){
            val result = TvSeries(
                id = i,
                title = "title $i",
                overview = "overview $i",
                originalLanguage = "originalLang $i",
                originalTitle = "originalTitle $i",
                posterPath = "posterPath $i",
                backdropPath = "backdropPath $i",
                firstAirDate = "firstAirDate $i",
                genres = listOf("fiction", "drama", "romance"),
                voteAverage = 7.332
            )
            dummyTvSeriesData.add(result)

        }
        return dummyTvSeriesData
    }
    fun dummyListMovieData(): List<Movie> {
        val dummyMovieData = ArrayList<Movie>()
        for (i in 0..2) {
            val result = Movie(
                movieId = i,
                title = "Movie ${i + 1}",
                overview = "overview $i",
                originalLanguage = "originalLang $i",
                originalTitle = "originalTitle $i",
                posterPath = "posterPath $i",
                backdropPath = "backDrop $i",
                releaseDate = "releaseDate",
                genres = listOf("action", "horor", "war"),
                rating = 4.3
            )
            dummyMovieData.add(result)
        }
        return dummyMovieData
    }

    fun generateUpComingMovieResponse(): UpcomingmovieResponse {
        val listResultItem = ArrayList<ResultsItem>()
        for (i in 0..10) {
            val resultItem = ResultsItem(
                overview = "overview $i",
                originalLanguage = "lang $i",
                originalTitle = "tittle $i",
                video = false,
                title = "Up coming movie $i",
                genreIds = listOf(1, 2, 3),
                posterPath = "posterPath $i",
                backdropPath = "backdropPath $i",
                releaseDate = "releaseDate $i",
                popularity = 3.43,
                voteAverage = 3.21,
                id = i,
                adult = true,
                voteCount = i
            )
            listResultItem.add(resultItem)
        }
        val dates = Dates(
            maximum = "2023-11-29",
            minimum = "2023-11-08"
        )

        return UpcomingmovieResponse(
            dates = dates,
            page = 1,
            totalPages = 10,
            results = listResultItem,
            totalResults = 34
        )
    }


    fun generateListGenreListResponse(): GenresResponse {
        val genreItem = ArrayList<com.jhorgi.cinematica.core.domain.model.GenresItem>()
        for (i in 0..2) {
            val result = com.jhorgi.cinematica.core.domain.model.GenresItem(
                name = "genre ${i + 1}",
                id = i + 1
            )
            genreItem.add(result)
        }

        return GenresResponse(
            genres = genreItem
        )
    }

    fun generateListTvSeriesResponse(): TvListResponse{

        val listResultItem = ArrayList<TvResults>()
        for (i in 0..10) {
            val resultItem = TvResults(
                firstAirDate = "data $i",
                overview = "overview $i",
                originalLanguage = "lang $i",
                genreIds = listOf(1, 2, 3),
                posterPath = "posterPath $i",
                originCountry = listOf("china", "indonesia", "malay"),
                backdropPath = "backdropPath $i",
                originalName= "original name $i",
                popularity = 3.43,
                voteAverage = 3.21,
                name= "title $i",
                id = i,
                voteCount = i
            )
            listResultItem.add(resultItem)
        }

        return TvListResponse(
            page = 1,
            totalPages = 3,
            results = listResultItem,
            totalResults = 10
        )

    }

    fun generateCreditsResponse(): CreditsResponse {

        val cast = ArrayList<CastItem>()
        val crew = ArrayList<CrewItem>()

        for (i in 0 ..10){
            val result = CastItem(
                castId = i,
                character = "character $i",
                gender = i,
                creditId = "credit $i",
                knownForDepartment = "knowForDepartment $i",
                originalName = "originalName $i",
                popularity = 1.23,
                name = "name $i",
                profilePath = "profilePath $i",
                id= i,
                adult = true,
                order = i
            )
            cast.add(result)
        }
        for (i in 0 ..10){
            val result = CrewItem(
                gender = i,
                creditId = "credit $i",
                knownForDepartment = "knowForDepartment $i",
                originalName = "originalName $i",
                popularity = 1.23,
                name = "name $i",
                profilePath = "profilePath $i",
                id= i,
                adult = true,
                department = "departemt $i",
                job = "job $i"
            )
            crew.add(result)
        }

        return CreditsResponse(
            cast = cast ,
            id= 2,
            crew = crew
        )
    }

    fun generateMovieDetailsResponse(): MovieDetailsResponse {
        val genres = ArrayList<GenresItem>()
        val productionCountries = ArrayList<ProductionCountriesItem>()
        val spokenLanguages = ArrayList<SpokenLanguagesItem>()
        val productionCompanies = ArrayList<ProductionCompaniesItem>()

        for (i in 0..2) {
            val genre = GenresItem(
                id = i,
                name = "Genre $i"
            )
            genres.add(genre)

            val country = ProductionCountriesItem(
                iso31661 = "Country $i",
                name = "Country Name $i"
            )
            productionCountries.add(country)

            val language = SpokenLanguagesItem(
                name = "Language $i",
                iso6391 = "ISO Code $i",
                englishName = "English Name $i"
            )
            spokenLanguages.add(language)

            val company = ProductionCompaniesItem(
                logoPath = "Logo Path $i",
                name = "Company Name $i",
                id = i,
                originCountry = "Origin Country $i"
            )
            productionCompanies.add(company)
        }

        return MovieDetailsResponse(
            originalLanguage = "Original Language",
            imdbId = "IMDb ID",
            video = true,
            title = "Movie Title",
            backdropPath = "Backdrop Path",
            revenue = 1000000,
            genres = genres,
            popularity = 5.67,
            productionCountries = productionCountries,
            id = 1,
            voteCount = 1000,
            budget = 500000,
            overview = "Movie Overview",
            originalTitle = "Original Title",
            runtime = 120,
            posterPath = "Poster Path",
            spokenLanguages = spokenLanguages,
            productionCompanies = productionCompanies,
            releaseDate = "2023-11-06",
            voteAverage = 7.5,
            belongsToCollection = BelongsToCollection(
                backdropPath = "Collection Backdrop Path",
                name = "Collection Name",
                id = 2,
                posterPath = "Collection Poster Path"
            ),
            tagline = "Movie Tagline",
            adult = false,
            homepage = "https://example.com",
            status = "Released"
        )
    }


    fun generateTvDetailsResponse(): TvDetailsResponse {
        val networks = ArrayList<NetworksItem>()
        val genres = ArrayList<GenresTvItem>()
        val productionCountries = ArrayList<ProductionCountriesTvItem>()
        val spokenLanguages = ArrayList<SpokenLanguagesTvItem>()
        val seasons = ArrayList<SeasonsItem?>()

        for (i in 0 until 3) {
            val network = NetworksItem(
                logoPath = "Network Logo Path $i",
                name = "Network Name $i",
                id = i,
                originCountry = "Network Country $i"
            )
            networks.add(network)

            val genre = GenresTvItem(
                name = "Genre Name $i",
                id = i
            )
            genres.add(genre)

            val country = ProductionCountriesTvItem(
                iso31661 = "Country ISO $i",
                name = "Country Name $i"
            )
            productionCountries.add(country)

            val language = SpokenLanguagesTvItem(
                name = "Language Name $i",
                iso6391 = "Language ISO $i",
                englishName = "Language English Name $i"
            )
            spokenLanguages.add(language)

            val season = SeasonsItem(
                airDate = "Season Air Date $i",
                overview = "Season Overview $i",
                episodeCount = 10 + i,
                voteAverage = 7.5 + i * 0.5,
                name = "Season Name $i",
                seasonNumber = i,
                id = i,
                posterPath = "Season Poster Path $i"
            )
            seasons.add(season)
        }

        return TvDetailsResponse(
            originalLanguage = "Original Language",
            numberOfEpisodes = 30,
            networks = networks,
            type = "Type",
            backdropPath = "Backdrop Path",
            genres = genres,
            popularity = 8.9,
            productionCountries = productionCountries,
            id = 1,
            numberOfSeasons = 5,
            voteCount = 1000,
            firstAirDate = "2023-11-06",
            overview = "TV Show Overview",
            seasons = seasons,
            languages = listOf("Language 1", "Language 2"),
            createdBy = listOf("Creator 1", "Creator 2"),
            lastEpisodeToAir = LastEpisodeToAir(
                episodeType = "Episode Type",
                productionCode = "Production Code",
                overview = "Last Episode Overview",
                showId = 2,
                seasonNumber = 1,
                runtime = 45,
                airDate = "2023-11-06",
                episodeNumber = 10,
                voteAverage = 8.0,
                name = "Last Episode Name",
                id = 3,
                voteCount = 500
            ),
            posterPath = "Poster Path",
            originCountry = listOf("US", "UK"),
            spokenLanguages = spokenLanguages,
            productionCompanies = listOf("Company 1", "Company 2"),
            originalName = "Original TV Show Name",
            voteAverage = 8.2,
            name = "TV Show Name",
            tagline = "TV Show Tagline",
            episodeRunTime = listOf(30, 45, 60),
            adult = false,
            nextEpisodeToAir = null,
            inProduction = true,
            lastAirDate = "2023-11-06",
            homepage = "https://tvshow.example.com",
            status = "In Production"
        )
    }

}
package com.jhorgi.cinematica.core.data.source.remote.data

import com.jhorgi.cinematica.core.data.source.remote.network.ApiService
import com.jhorgi.cinematica.core.data.source.remote.response.CreditsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.GenresResponse
import com.jhorgi.cinematica.core.data.source.remote.response.ListMovieResponse
import com.jhorgi.cinematica.core.data.source.remote.response.MovieDetailsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.TvDetailsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.TvListResponse
import com.jhorgi.cinematica.core.data.source.remote.response.UpcomingmovieResponse
import com.jhorgi.cinematica.core.data.source.remote.utils.DataDummy
import com.jhorgi.cinematica.core.data.source.remote.utils.DataDummy.generateTvDetailsResponse

class FakeApiService : ApiService {

    override suspend fun getMovieList(page: Int): ListMovieResponse =
        DataDummy.generateListMovieResponse()

    override suspend fun movieListGenres(): GenresResponse {
        val genreList = ArrayList<com.jhorgi.cinematica.core.domain.model.GenresItem>()
        for (i in 0..5) {
            val response = com.jhorgi.cinematica.core.domain.model.GenresItem(
                name = "genre $i",
                id = i
            )

            genreList.add(response)
        }


        return GenresResponse(genreList)
    }

    override suspend fun tvListGenres(): GenresResponse {
        val genreList = ArrayList<com.jhorgi.cinematica.core.domain.model.GenresItem>()
        for (i in 0..5) {
            val response = com.jhorgi.cinematica.core.domain.model.GenresItem(
                name = "genre $i",
                id = i
            )

            genreList.add(response)
        }


        return GenresResponse(genreList)
    }

    override suspend fun getPopularMovie(page: Int): ListMovieResponse =
        DataDummy.generateListMovieResponse()


    override suspend fun getUpComingMovie(page: Int): UpcomingmovieResponse =
        DataDummy.generateUpComingMovieResponse()

    override suspend fun getTopRatedMovie(page: Int): ListMovieResponse =
        DataDummy.generateListMovieResponse()

    override suspend fun getNowPlayingMovie(page: Int): ListMovieResponse =
        DataDummy.generateListMovieResponse()

    override suspend fun getPopularTvShow(page: Int): TvListResponse =
        DataDummy.generateListTvSeriesResponse()

    override suspend fun getTopRatedTvShows(page: Int): TvListResponse =
        DataDummy.generateListTvSeriesResponse()

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse = DataDummy.generateMovieDetailsResponse()

    override suspend fun getTvDetails(seriesId: Int): TvDetailsResponse = generateTvDetailsResponse()

    override suspend fun getMovieCredits(movieId: Int): CreditsResponse =
        DataDummy.generateCreditsResponse()

    override suspend fun getSeriesCredits(seriesId: Int): CreditsResponse =
        DataDummy.generateCreditsResponse()

    override suspend fun discoverMovie(
        language: String,
        page: Int,
        sortBy: String
    ): ListMovieResponse = DataDummy.generateListMovieResponse()

    override suspend fun discoverTvShow(
        language: String,
        page: Int,
        sortBy: String
    ): TvListResponse = DataDummy.generateListTvSeriesResponse()

    override suspend fun getTrendingMovie(timeWindow: String, page: Int): ListMovieResponse =
        DataDummy.generateListMovieResponse()

    override suspend fun getTrendingTvSeries(timeWindow: String, page: Int): TvListResponse =
        DataDummy.generateListTvSeriesResponse()

    override suspend fun moviesSearch(query: String): ListMovieResponse =
        DataDummy.generateListMovieResponse()

    override suspend fun tvSearch(query: String): TvListResponse =
        DataDummy.generateListTvSeriesResponse()

}
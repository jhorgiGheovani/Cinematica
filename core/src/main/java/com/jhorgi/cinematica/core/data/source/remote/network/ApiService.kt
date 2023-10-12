package com.jhorgi.cinematica.core.data.source.remote.network

import com.jhorgi.cinematica.core.data.source.remote.response.CreditsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.GenresResponse
import com.jhorgi.cinematica.core.data.source.remote.response.ListMovieResponse
import com.jhorgi.cinematica.core.data.source.remote.response.MovieDetailsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.TvDetailsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.TvListResponse
import com.jhorgi.cinematica.core.data.source.remote.response.UpcomingmovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //Delete  getMovieList later
    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("page") page: Int = 1
    ): ListMovieResponse

    @GET("genre/movie/list")
    suspend fun movieListGenres(): GenresResponse

    @GET("genre/tv/list")
    suspend fun tvListGenres(): GenresResponse
    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int = 1
    ): ListMovieResponse

    @GET("movie/upcoming")
    suspend fun getUpComingMovie(
        @Query("page") page: Int = 1
    ): UpcomingmovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("page") page: Int = 1
    ): ListMovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("page") page: Int = 1
    ): ListMovieResponse
    @GET("tv/popular")
    suspend fun getPopularTvShow(
        @Query("page") page: Int = 1
    ): TvListResponse

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("page") page: Int = 1
    ): TvListResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int
    ): MovieDetailsResponse

    @GET("tv/{seriesId}")
    suspend fun getTvDetails(
        @Path("seriesId") seriesId: Int
    ): TvDetailsResponse

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCredits(
        @Path("movieId") movieId: Int
    ): CreditsResponse

    @GET("tv/{seriesId}/aggregate_credits")
    suspend fun getSeriesCredits(
        @Path("seriesId") seriesId: Int
    ): CreditsResponse

    @GET("discover/movie")
    suspend fun discoverMovie(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): ListMovieResponse

    @GET("discover/tv")
    suspend fun discoverTvShow(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sortBy") sortBy: String = "popularity.desc"
    ): TvListResponse

    @GET("trending/movie/{timeWindow}")
    suspend fun getTrendingMovie(
        @Path("timeWindow") timeWindow: String = "week",
        @Query("page") page: Int = 1,
    ): ListMovieResponse

    @GET("trending/tv/{timeWindow}")
    suspend fun getTrendingTvSeries(
        @Path("timeWindow") timeWindow: String = "week",
        @Query("page") page: Int = 1,
    ): TvListResponse

    @GET("search/movie")
    suspend fun moviesSearch(
        @Query("query") query: String
    ):ListMovieResponse

}
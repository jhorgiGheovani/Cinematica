package com.jhorgi.cinematica.core.data.source.remote.network

import com.jhorgi.cinematica.core.data.source.remote.response.CreditsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.MovieDetailsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.PopularMovieResponse
import com.jhorgi.cinematica.core.data.source.remote.response.TvDetailsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.TvListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("page") page: Int = 1
    ): PopularMovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int
    ): MovieDetailsResponse

    @GET("tv/{series_id}")
    suspend fun getTvDetails(
        @Path("series_id") series_id: Int
    ): TvDetailsResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movie_id: Int
    ): CreditsResponse

    @GET("tv/{series_id}/aggregate_credits")
    suspend fun getSeriesCredits(
        @Path("series_id") series_id: Int
    ): CreditsResponse

    @GET("discover/movie")
    suspend fun discoverMovie(
        @Query("include_adult") include_adult: Boolean = false,
        @Query("include_video") include_video: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): PopularMovieResponse

    @GET("discover/tv")
    suspend fun discoverTvShow(
        @Query("include_adult") include_adult: Boolean = false,
        @Query("include_video") include_video: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): TvListResponse

    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovie(
        @Path("time_window") time_window: String = "week",
        @Query("page") page: Int = 1,
    ):PopularMovieResponse

    @GET("trending/tv/{time_window}")
    suspend fun getTrendingTvSeries(
        @Path("time_window") time_window: String = "week",
        @Query("page") page: Int = 1,
    ):TvListResponse

}
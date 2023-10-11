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
    ): ListMovieResponse

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
    ): ListMovieResponse

    @GET("trending/tv/{time_window}")
    suspend fun getTrendingTvSeries(
        @Path("time_window") time_window: String = "week",
        @Query("page") page: Int = 1,
    ): TvListResponse

    @GET("search/movie")
    suspend fun moviesSearch(
        @Query("query") query: String
    ):ListMovieResponse

}
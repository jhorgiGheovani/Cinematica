package com.jhorgi.cinematica.core.data.source.remote.network

import com.jhorgi.cinematica.core.data.source.remote.response.CreditsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.MovieDetailsResponse
import com.jhorgi.cinematica.core.data.source.remote.response.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("popular")
    suspend fun getMovieList(
        @Query("page") page: Int
    ): PopularMovieResponse

    @GET("{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int
    ): MovieDetailsResponse

    @GET("{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movie_id: Int
    ): CreditsResponse

}
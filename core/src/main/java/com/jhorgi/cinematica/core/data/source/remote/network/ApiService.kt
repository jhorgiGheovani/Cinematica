package com.jhorgi.cinematica.core.data.source.remote.network

import com.jhorgi.cinematica.core.data.source.remote.response.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("popular")
    suspend fun getMovieList(
        @Query("page") page: Int
    ): PopularMovieResponse

}
package com.jhorgi.cinematica.core.data.source.remote

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jhorgi.cinematica.core.data.MoviePagingSource
import com.jhorgi.cinematica.core.data.source.remote.network.ApiService
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

const val NETWORK_PAGE_SIZE = 25

class RemoteDataSource(private val apiService: ApiService) {
    fun getMovie(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiService = apiService)
            }
        ).flow
    }

    fun getMovieDetails(movie_id: Int): Flow<MovieDetails> = flow {
        try {
            val response = apiService.getMovieDetails(movie_id)
            val movieDetails = DataMapper.mapMovieDetailsResponseToMovieDetails(response)
            emit(movieDetails)
        }catch (e: Exception){
            Log.d("Error getMovieDetails", e.message.toString())
        }
    }

    fun getCast(movie_id: Int): Flow<List<Credit>> = flow {
        try {
            val response = apiService.getCredits(movie_id)
            val cast = DataMapper.mapCreditsResponseToCastCreditModel(response)
            emit(cast)
        } catch (e: Exception) {
            Log.d("Error getCast", e.message.toString())
        }
    }

    fun getCrews(movie_id: Int): Flow<List<Credit>> = flow {
        try {
            val response = apiService.getCredits(movie_id)
            val crewList = DataMapper.mapCreditsResponseToCrewCreditModel(response)
            emit(crewList)

        } catch (e: Exception) {
            Log.d("Error getCrews", e.message.toString())
        }
    }


}
package com.jhorgi.cinematica.core.data.source.remote

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.core.data.pagingDataSource.DiscoverMoviePagingSource
import com.jhorgi.cinematica.core.data.pagingDataSource.DiscoverTvSeriesPagingSource
import com.jhorgi.cinematica.core.data.pagingDataSource.MoviePagingSource
import com.jhorgi.cinematica.core.data.source.remote.network.ApiService
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.core.domain.model.TvSeriesDetails
import com.jhorgi.cinematica.core.utils.DataMapper
import kotlinx.coroutines.delay
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
        } catch (e: Exception) {
            Log.d("Error getMovieDetails", e.message.toString())
        }
    }

    fun getTvSeriesDetails(series_id: Int): Flow<TvSeriesDetails> = flow {
        try {
            val response = apiService.getTvDetails(series_id)
            val tvSeriesDetails = DataMapper.mapTvDetailsResponseToDomain(response)
            Log.d("FirstAirDate", tvSeriesDetails.toString())
            emit(tvSeriesDetails)
        } catch (e: Exception) {
            Log.d("Error getTvSeriesDetails", e.message.toString())
        }
    }

    fun getMovieCast(movie_id: Int): Flow<List<Credit>> = flow {
        try {
            val response = apiService.getMovieCredits(movie_id)
            val cast = DataMapper.mapCreditsResponseToCastCreditModel(response)
            emit(cast)
        } catch (e: Exception) {
            Log.d("Error getMovieCast", e.message.toString())
        }
    }

    fun getMovieCrews(movie_id: Int): Flow<List<Credit>> = flow {
        try {
            val response = apiService.getMovieCredits(movie_id)
            val crewList = DataMapper.mapCreditsResponseToCrewCreditModel(response)
            emit(crewList)

        } catch (e: Exception) {
            Log.d("Error getMovieCrews", e.message.toString())
        }
    }

    fun getTvCast(series_id: Int): Flow<List<Credit>> = flow {
        try {
            val response = apiService.getSeriesCredits(series_id)
            val cast = DataMapper.mapCreditsResponseToCastCreditModel(response)
            Log.d("credits getTvCast", cast.toString())
            emit(cast)
        } catch (e: Exception) {
            Log.d("Error getTvCast", e.message.toString())
        }
    }

    fun getTvCrews(series_id: Int): Flow<List<Credit>> = flow {
        try {
            val response = apiService.getSeriesCredits(series_id)
            val crewList = DataMapper.mapCreditsResponseToCrewCreditModel(response)
            emit(crewList)

        } catch (e: Exception) {
            Log.d("Error getTvCrews", e.message.toString())
        }
    }

    fun discoverMovie(): Flow<Resource<List<Movie>>> = flow {
        try {
            delay(2000)
            val response = apiService.discoverMovie().results.map {
                DataMapper.mapMovieResponseToDomain(it)
            }
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit( Resource.Error(e.message.toString()))
        }
    }

    fun getDiscoverMovieWithPaging(): Flow<PagingData<Movie>>{
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                DiscoverMoviePagingSource(apiService)
            }
        ).flow
    }

    fun getDiscoverTvSeriesWithPaging(): Flow<PagingData<TvSeries>>{
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                DiscoverTvSeriesPagingSource(apiService)
            }
        ).flow
    }

    fun discoverTvShow(): Flow<Resource<List<TvSeries>>> = flow {
        try {
            val response = apiService.discoverTvShow().results.map {
                DataMapper.mapTvShowResponseToDomain(it)
            }
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit( Resource.Error(e.message.toString()))
            Log.d("Error RemoteDatasource discoverTvShow", e.message.toString())
        }
    }

}
package com.jhorgi.cinematica.core.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jhorgi.cinematica.core.data.MoviePagingSource
import com.jhorgi.cinematica.core.data.source.remote.network.ApiService
import com.jhorgi.cinematica.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

const val NETWORK_PAGE_SIZE = 20
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
}
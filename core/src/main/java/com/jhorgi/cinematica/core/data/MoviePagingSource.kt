package com.jhorgi.cinematica.core.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jhorgi.cinematica.core.data.source.remote.network.ApiService
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.utils.DataMapper

class MoviePagingSource(private val apiService: ApiService): PagingSource<Int, Movie>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)

            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val movie = apiService.getMovieList(page).results

            val dataMapped  = movie.map {
                DataMapper.mapResponseToDomain(it)
            }

            val nextKey =
                if (dataMapped.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    page + 1
                }


            LoadResult.Page(
                data = dataMapped,
                prevKey = if(page == INITIAL_PAGE_INDEX) null else page,
                nextKey = nextKey
            )

        }catch (e: Exception){
         return LoadResult.Error(Throwable(e.message))
        }
    }
}
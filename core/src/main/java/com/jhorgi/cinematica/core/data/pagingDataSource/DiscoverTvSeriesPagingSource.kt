package com.jhorgi.cinematica.core.data.pagingDataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jhorgi.cinematica.core.data.source.remote.network.ApiService
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.core.utils.DataMapper

class DiscoverTvSeriesPagingSource(private val apiService: ApiService): PagingSource<Int, TvSeries>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, TvSeries>): Int? {
        return state.anchorPosition?.let { anchorPostion ->
            val anchorPage = state.closestPageToPosition(anchorPostion)

            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvSeries> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX

            val emptyGenreList = listOf<String>()
            val dataMapped = apiService.discoverTvShow(page= page).results.map {
                DataMapper.mapTvShowResponseToDomain(it,emptyGenreList)
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
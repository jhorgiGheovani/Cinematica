package com.jhorgi.cinematica.core.data.pagingDataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jhorgi.cinematica.core.data.source.remote.network.ApiService
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.core.utils.DataMapper

class TvSeriesPagingSource(private val apiService: ApiService, private val requestData: String) :
    PagingSource<Int, TvSeries>() {

    companion object {
        const val INITIAL_PAGE_INDEX = 1
        const val ARG_DISCOVER_TV_SERIES = "discover_tv_series"
        const val ARG_POPULAR_TV_SERIES = "popular_tv_series"
        const val ARG_TOP_RATED_TV_SERIES = "top_rated_tv_series"
    }

    override fun getRefreshKey(state: PagingState<Int, TvSeries>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)

            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvSeries> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val genreList = apiService.tvListGenres().genres  //1. Fetch List<Genres>

            val idToMap = genreList.associateBy { it.id }

            var dataMapped = emptyList<TvSeries>()

            //Discover Tv Series
            if (requestData == ARG_DISCOVER_TV_SERIES) {
                dataMapped = apiService.discoverTvShow(page = page).results.map {
                    val listOfGenre = it.genreIds?.mapNotNull { id ->
                        idToMap[id]?.name  //3. map the List<Id> to id key
                    }
                    DataMapper.mapTvShowResponseToDomain(it, listOfGenre!!)
                }
            }

            //Popular Tv Series
            if(requestData == ARG_POPULAR_TV_SERIES){
                dataMapped = apiService.getPopularTvShow(page = page).results.map {
                    val listOfGenre = it.genreIds?.mapNotNull { id ->
                        idToMap[id]?.name  //3. map the List<Id> to id key
                    }
                    DataMapper.mapTvShowResponseToDomain(it, listOfGenre!!)
                }
            }

            //Top Rated Tv Series
            if(requestData == ARG_TOP_RATED_TV_SERIES){
                dataMapped = apiService.getTopRatedTvShows(page = page).results.map {
                    val listOfGenre = it.genreIds?.mapNotNull { id ->
                        idToMap[id]?.name  //3. map the List<Id> to id key
                    }
                    DataMapper.mapTvShowResponseToDomain(it, listOfGenre!!)
                }
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
                prevKey = if (page == INITIAL_PAGE_INDEX) null else page,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            return LoadResult.Error(Throwable(e.message))
        }
    }


}
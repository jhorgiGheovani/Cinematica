package com.jhorgi.cinematica.core.data.pagingDataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jhorgi.cinematica.core.data.source.remote.network.ApiService
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.utils.DataMapper

class MoviePagingSource(private val apiService: ApiService, private val requestData: String) :
    PagingSource<Int, Movie>() {

    companion object {
        const val INITIAL_PAGE_INDEX = 1
        const val ARG_UP_COMING_MOVIE = "upcoming_movie"
        const val ARG_TOP_RATED_MOVIE = "top_rated_movie"
        const val ARG_NOW_PLAYING_MOVIE = "now_playing_movie"
        const val ARG_DISCOVER_MOVIE = "discover_movie"
        const val ARG_POPULAR_MOVIE = "popular_movie"
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
            val genreList = apiService.movieListGenres().genres  //1. Fetch List<Genres>

            val idToMap = genreList.associateBy { it.id }

            var dataMapped = emptyList<Movie>()

            //Discover Movie
            if(requestData == ARG_DISCOVER_MOVIE){
                dataMapped = apiService.discoverMovie(page= page).results.map {
                    val listOfGenre = it.genreIds.mapNotNull{id->
                        idToMap[id]?.name  //3. map the List<Id> to id key
                    }
                    DataMapper.mapMovieResponseToDomain(it,listOfGenre)
                }
            }

            //Upcoming Movie
            if(requestData == ARG_UP_COMING_MOVIE){
                dataMapped = apiService.getUpComingMovie(page = page).results.map {
                    val listOfGenre = it.genreIds.mapNotNull{id->
                        idToMap[id]?.name  //3. map the List<Id> to id key
                    }
                    DataMapper.mapMovieResponseToDomain(it,listOfGenre)
                }
            }

            //Popular Movie
            if(requestData == ARG_POPULAR_MOVIE){
                dataMapped = apiService.getPopularMovie(page = page).results.map {
                    val listOfGenre = it.genreIds.mapNotNull{id->
                        idToMap[id]?.name  //3. map the List<Id> to id key
                    }
                    DataMapper.mapMovieResponseToDomain(it,listOfGenre)
                }
            }

            //Top Rated Movie
            if(requestData == ARG_TOP_RATED_MOVIE){
                dataMapped = apiService.getTopRatedMovie(page = page).results.map {
                    val listOfGenre = it.genreIds.mapNotNull{id->
                        idToMap[id]?.name  //3. map the List<Id> to id key
                    }
                    DataMapper.mapMovieResponseToDomain(it,listOfGenre)
                }
            }

            //Now Playing Movie
            if(requestData == ARG_NOW_PLAYING_MOVIE){
                dataMapped = apiService.getNowPlayingMovie(page = page).results.map {
                    val listOfGenre = it.genreIds.mapNotNull{id->
                        idToMap[id]?.name  //3. map the List<Id> to id key
                    }
                    DataMapper.mapMovieResponseToDomain(it,listOfGenre)
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
                prevKey = if(page == INITIAL_PAGE_INDEX) null else page,
                nextKey = nextKey
            )

        }catch (e: Exception){
            return LoadResult.Error(Throwable(e.message))
        }
    }
}
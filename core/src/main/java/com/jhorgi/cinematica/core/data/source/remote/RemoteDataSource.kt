package com.jhorgi.cinematica.core.data.source.remote

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.core.data.pagingDataSource.MoviePagingSource
import com.jhorgi.cinematica.core.data.pagingDataSource.TvSeriesPagingSource
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


    fun getPopularMovie(): Flow<Resource<List<Movie>>> = flow {
        try {

            val genreList = apiService.movieListGenres().genres  //1. Fetch List<Genres>

            val idToMap = genreList.associateBy { it.id }   //2. use associateBy to get id key

            val response = apiService.getPopularMovie().results.map {
                val listOfGenre = it.genreIds.mapNotNull { id ->
                    idToMap[id]?.name  //3. map the List<Id> to id key
                }
                DataMapper.mapMovieResponseToDomain(it, listOfGenre)
            }
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun getPopularMovieWithPaging(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiService, MoviePagingSource.ARG_POPULAR_MOVIE)
            }
        ).flow
    }

    fun getUpComingMovie(): Flow<Resource<List<Movie>>> = flow {
        try {

            val response = apiService.getUpComingMovie().results.map { data ->

                val emptyList = listOf<String>()
                DataMapper.mapMovieResponseToDomain(data, emptyList)
            }
            emit(Resource.Success(response))

        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun getUpComingMovieWithPaging(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiService, MoviePagingSource.ARG_UP_COMING_MOVIE)
            }
        ).flow

    }

    fun getTopRatedMovie(): Flow<Resource<List<Movie>>> = flow {
        try {

            val genreList = apiService.movieListGenres().genres  //1. Fetch List<Genres>

            val idToMap = genreList.associateBy { it.id }   //2. use associateBy to get id key


            val response = apiService.getTopRatedMovie().results.map { data ->

                val listOfGenre = data.genreIds.mapNotNull { id ->
                    idToMap[id]?.name  //3. map the List<Id> to id key
                }

                DataMapper.mapMovieResponseToDomain(data, listOfGenre)
            }
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun getTopRatedMovieWithPaging(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiService, MoviePagingSource.ARG_TOP_RATED_MOVIE)
            }
        ).flow
    }

    fun getNowPlayingMovie(): Flow<Resource<List<Movie>>> = flow {
        try {

            val genreList = apiService.movieListGenres().genres  //1. Fetch List<Genres>

            val idToMap = genreList.associateBy { it.id }   //2. use associateBy to get id key

            val response = apiService.getNowPlayingMovie().results.map { data ->
                val listOfGenre = data.genreIds.mapNotNull { id ->
                    idToMap[id]?.name  //3. map the List<Id> to id key
                }

                DataMapper.mapMovieResponseToDomain(data, listOfGenre)
            }
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun getNowPlayingMovieWithPaging(): Flow<PagingData<Movie>>{
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiService, MoviePagingSource.ARG_NOW_PLAYING_MOVIE)
            }
        ).flow
    }

    fun getPopularTvShow(): Flow<Resource<List<TvSeries>>> = flow {
        try {

            val genre = apiService.tvListGenres().genres
            val idToMap = genre.associateBy { it.id }
            val response = apiService.getPopularTvShow().results.map { data ->
                val listOfGenre = data.genreIds?.mapNotNull { id ->
                    idToMap[id]?.name  //3. map the List<Id> to id key
                }

                DataMapper.mapTvShowResponseToDomain(data, listOfGenre!!)
            }
            emit(Resource.Success(response))

        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun getPopularTvShowWithPaging(): Flow<PagingData<TvSeries>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                TvSeriesPagingSource(apiService, TvSeriesPagingSource.ARG_POPULAR_TV_SERIES)
            }
        ).flow
    }

    fun getTopRatedTvShows(): Flow<Resource<List<TvSeries>>> = flow {
        try {

            val genre = apiService.tvListGenres().genres
            val idToMap = genre.associateBy { it.id }
            val response = apiService.getPopularTvShow().results.map { data ->
                val listOfGenre = data.genreIds?.mapNotNull { id ->
                    idToMap[id]?.name  //3. map the List<Id> to id key
                }

                DataMapper.mapTvShowResponseToDomain(data, listOfGenre!!)
            }
            emit(Resource.Success(response))

        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun getTopRatedTvShowsWithPaging(): Flow<PagingData<TvSeries>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                TvSeriesPagingSource(apiService, TvSeriesPagingSource.ARG_TOP_RATED_TV_SERIES)
            }
        ).flow
    }

    fun getMovieDetails(movieId: Int): Flow<MovieDetails> = flow {
        try {
            val response = apiService.getMovieDetails(movieId)
            val movieDetails = DataMapper.mapMovieDetailsResponseToMovieDetails(response)
            emit(movieDetails)
        } catch (e: Exception) {
            Log.d("Error getMovieDetails", e.message.toString())
        }
    }

    fun getTvSeriesDetails(seriesId: Int): Flow<TvSeriesDetails> = flow {
        try {
            val response = apiService.getTvDetails(seriesId)
            val tvSeriesDetails = DataMapper.mapTvDetailsResponseToDomain(response)

            emit(tvSeriesDetails)
        } catch (e: Exception) {
            Log.d("Error getTvSeriesDetails", e.message.toString())
        }
    }

    fun getMovieCast(movieId: Int): Flow<List<Credit>> = flow {
        try {
            val response = apiService.getMovieCredits(movieId)
            val cast = DataMapper.mapCreditsResponseToCastCreditModel(response)
            emit(cast)
        } catch (e: Exception) {
            Log.d("Error getMovieCast", e.message.toString())
        }
    }

    fun getMovieCrews(movieId: Int): Flow<List<Credit>> = flow {
        try {
            val response = apiService.getMovieCredits(movieId)
            val crewList = DataMapper.mapCreditsResponseToCrewCreditModel(response)
            emit(crewList)

        } catch (e: Exception) {
            Log.d("Error getMovieCrews", e.message.toString())
        }
    }

    fun getTvCast(seriesId: Int): Flow<List<Credit>> = flow {
        try {
            val response = apiService.getSeriesCredits(seriesId)
            val cast = DataMapper.mapCreditsResponseToCastCreditModel(response)
            Log.d("credits getTvCast", cast.toString())
            emit(cast)
        } catch (e: Exception) {
            Log.d("Error getTvCast", e.message.toString())
        }
    }

    fun getTvCrews(seriesId: Int): Flow<List<Credit>> = flow {
        try {
            val response = apiService.getSeriesCredits(seriesId)
            val crewList = DataMapper.mapCreditsResponseToCrewCreditModel(response)
            emit(crewList)

        } catch (e: Exception) {
            Log.d("Error getTvCrews", e.message.toString())
        }
    }

    fun discoverMovie(): Flow<Resource<List<Movie>>> = flow {
        try {
            delay(1000)

            val genreList = apiService.movieListGenres().genres  //1. Fetch List<Genres>

            val idToMap = genreList.associateBy { it.id }   //2. use associateBy to get id key

            val response = apiService.discoverMovie().results.map {
                val listOfGenre = it.genreIds.mapNotNull { id ->
                    idToMap[id]?.name  //3. map the List<Id> to id key
                }
                DataMapper.mapMovieResponseToDomain(it, listOfGenre)
            }
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun getDiscoverMovieWithPaging(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiService,MoviePagingSource.ARG_DISCOVER_MOVIE)
            }
        ).flow
    }

    fun getDiscoverTvSeriesWithPaging(): Flow<PagingData<TvSeries>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                TvSeriesPagingSource(apiService, TvSeriesPagingSource.ARG_DISCOVER_TV_SERIES)
            }
        ).flow
    }

    fun discoverTvShow(): Flow<Resource<List<TvSeries>>> = flow {
        try {

            val genre = apiService.tvListGenres().genres
            val idToMap = genre.associateBy { it.id }

            val response = apiService.discoverTvShow().results.map {

                val listOfGenre = it.genreIds?.mapNotNull { id ->
                    idToMap[id]?.name  //3. map the List<Id> to id key
                }

                DataMapper.mapTvShowResponseToDomain(it, listOfGenre!!)
            }
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
            Log.d("Error RemoteDatasource discoverTvShow", e.message.toString())
        }
    }

    fun movieSearch(query: String): Flow<Resource<List<Movie>>> = flow {
        try {

            val genreList = apiService.movieListGenres().genres  //1. Fetch List<Genres>

            val idToMap = genreList.associateBy { it.id }   //2. use associateBy to get id key

            val response = apiService.moviesSearch(query).results.map {
                val listOfGenre = it.genreIds.mapNotNull { id ->
                    idToMap[id]?.name  //3. map the List<Id> to id key
                }
//                val rating = it.voteAverage.
                val roundedValue = "%.1f".format(it.voteAverage).toDouble()
                DataMapper.mapMovieSearchToDomain(it, listOfGenre, roundedValue)
            }

            emit(Resource.Success(response))


        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun tvSeriesSearch(query: String): Flow<Resource<List<TvSeries>>> = flow {
        try {

            val genreList = apiService.tvListGenres().genres  //1. Fetch List<Genres>

            val idToMap = genreList.associateBy { it.id }   //2. use associateBy to get id key

            val response = apiService.tvSearch(query).results.map {
                val listOfGenre = it.genreIds?.mapNotNull { id ->
                    idToMap[id]?.name  //3. map the List<Id> to id key
                }
                val roundedValue = "%.1f".format(it.voteAverage).toDouble()
                DataMapper.mapTvShowSearchResponseToDomain(it, listOfGenre!!,roundedValue)
            }

            emit(Resource.Success(response))


        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }


}
package com.jhorgi.cinematica.core.data

import androidx.paging.PagingData
import com.jhorgi.cinematica.core.data.source.local.LocalDataSource
import com.jhorgi.cinematica.core.data.source.remote.RemoteDataSource
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.FavoriteItem
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.core.domain.model.TvSeriesDetails
import com.jhorgi.cinematica.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : IMovieRepository {
    override fun getPopularMovie(): Flow<Resource<List<Movie>>> = remoteDataSource.getPopularMovie()
    override fun getPopularMovieWithPaging(): Flow<PagingData<Movie>> = remoteDataSource.getPopularMovieWithPaging()

    override fun getTopRatedMovie(): Flow<Resource<List<Movie>>> = remoteDataSource.getTopRatedMovie()
    override fun getTopRatedMovieWithPaging(): Flow<PagingData<Movie>> = remoteDataSource.getTopRatedMovieWithPaging()

    override fun getNowPlayingMovie(): Flow<Resource<List<Movie>>> = remoteDataSource.getNowPlayingMovie()
    override fun getNowPlayingMovieWithPaging(): Flow<PagingData<Movie>>  = remoteDataSource.getNowPlayingMovieWithPaging()

    override fun getPopularTvShow(): Flow<Resource<List<TvSeries>>> = remoteDataSource.getPopularTvShow()
    override fun getPopularTvShowWithPaging(): Flow<PagingData<TvSeries>> = remoteDataSource.getPopularTvShowWithPaging()

    override fun getTopRatedTvShows(): Flow<Resource<List<TvSeries>>> = remoteDataSource.getTopRatedTvShows()
    override fun getTopRatedTvShowsWithPaging(): Flow<PagingData<TvSeries>>  = remoteDataSource.getTopRatedTvShowsWithPaging()
    override fun movieSearch(query: String): Flow<Resource<List<Movie>>> = remoteDataSource.movieSearch(query)
    override fun tvSeriesSearch(query: String): Flow<Resource<List<TvSeries>>> = remoteDataSource.tvSeriesSearch(query)

    override fun getUpComingMovie(): Flow<Resource<List<Movie>>> = remoteDataSource.getUpComingMovie()
    override fun getUpComingMovieWithPaging(): Flow<PagingData<Movie>>  = remoteDataSource.getUpComingMovieWithPaging()

    override fun discoverMovie(): Flow<Resource<List<Movie>>> = remoteDataSource.discoverMovie()
    override fun getDiscoverMovieWithPaging(): Flow<PagingData<Movie>> =
        remoteDataSource.getDiscoverMovieWithPaging()

    override fun discoverTvShow(): Flow<Resource<List<TvSeries>>> = remoteDataSource.discoverTvShow()

    override fun getDiscoverTvSeriesWithPaging(): Flow<PagingData<TvSeries>> =
        remoteDataSource.getDiscoverTvSeriesWithPaging()

    override fun getMovieDetails(movie_id: Int): Flow<MovieDetails> =
        remoteDataSource.getMovieDetails(movie_id)

    override fun getTvSeriesDetails(series_id: Int): Flow<TvSeriesDetails> =
        remoteDataSource.getTvSeriesDetails(series_id)

    override fun getMovieCast(movie_id: Int): Flow<List<Credit>> =
        remoteDataSource.getMovieCast(movie_id)

    override fun getMovieCrew(movie_id: Int): Flow<List<Credit>> =
        remoteDataSource.getMovieCrews(movie_id)

    override fun getTvCast(series_id: Int): Flow<List<Credit>> =
        remoteDataSource.getTvCast(series_id)

    override fun getTvCrew(series_id: Int): Flow<List<Credit>> =
        remoteDataSource.getTvCrews(series_id)

    override suspend fun addMovieToFavorite(
        movie: FavoriteItem,
        timeStamp: Long,
        category: String,
        rating:String
    ) {
        return localDataSource.addMovieToFavorite(movie, timeStamp, category, rating)
    }

    override suspend fun deleteMovieFromFavorite(itemId: Int) {
        return localDataSource.deleteItemById(itemId)
    }

    override fun getListOfFavoriteItem(category: String): Flow<List<FavoriteItem>> =
        localDataSource.getListOfFavoriteItem(category)

    override fun isFavoriteItem(movie_id: Int): Flow<Boolean> =
        localDataSource.isFavoriteItem(movie_id)

}
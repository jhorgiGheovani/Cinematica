package com.jhorgi.cinematica.core.domain.usecase

import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.core.data.pagingDataSource.MoviePagingSource
import com.jhorgi.cinematica.core.data.pagingDataSource.TvSeriesPagingSource
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.FavoriteItem
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.core.domain.model.TvSeriesDetails
import com.jhorgi.cinematica.core.domain.repository.IMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getPopularMovie(): Flow<Resource<List<Movie>>> =movieRepository.getPopularMovie()
    override fun moviePagingSource(argument: String): MoviePagingSource = movieRepository.moviePagingSource(argument= argument)
    override fun tvSeriesPagingSource(argument: String): TvSeriesPagingSource = movieRepository.tvSeriesPagingSource(argument = argument)

    override fun getTopRatedMovie(): Flow<Resource<List<Movie>>> = movieRepository.getTopRatedMovie()

    override fun getNowPlayingMovie(): Flow<Resource<List<Movie>>> = movieRepository.getNowPlayingMovie()

    override fun getPopularTvShow(): Flow<Resource<List<TvSeries>>> = movieRepository.getPopularTvShow()

    override fun getTopRatedTvShows(): Flow<Resource<List<TvSeries>>> = movieRepository.getTopRatedTvShows()

    override fun movieSearch(query: String): Flow<Resource<List<Movie>>> = movieRepository.movieSearch(query)
    override fun tvSeriesSearch(query: String): Flow<Resource<List<TvSeries>>> = movieRepository.tvSeriesSearch(query)

    override fun getUpComingMovie(): Flow<Resource<List<Movie>>> = movieRepository.getUpComingMovie()

    override fun discoverMovie(): Flow<Resource<List<Movie>>> = movieRepository.discoverMovie()

    override fun discoverTvShow(): Flow<Resource<List<TvSeries>>> = movieRepository.discoverTvShow()


    override fun getMovieDetails(movieId: Int): Flow<MovieDetails> =
        movieRepository.getMovieDetails(movieId)

    override fun getTvSeriesDetails(seriesId: Int): Flow<TvSeriesDetails> =
        movieRepository.getTvSeriesDetails(seriesId)

    override fun getMovieCast(movieId: Int): Flow<List<Credit>> =
        movieRepository.getMovieCast(movieId).flowOn(Dispatchers.IO)

    override fun getMovieCrew(movieId: Int): Flow<List<Credit>> =
        movieRepository.getMovieCrew(movieId).flowOn(Dispatchers.IO)

    override fun getTvCast(seriesId: Int): Flow<List<Credit>> =
        movieRepository.getTvCast(seriesId)

    override fun getTvCrew(seriesId: Int): Flow<List<Credit>> =
        movieRepository.getTvCrew(seriesId)

    override suspend fun addItemToFavorite(movie: FavoriteItem, timeStamp: Long, category: String, rating: String) =
        movieRepository.addMovieToFavorite(movie, timeStamp, category, rating)

    override suspend fun deleteMovieFromFavorite(movieId: Int) =
        movieRepository.deleteMovieFromFavorite(movieId)

    override fun getListOfFavoriteItem(category: String): Flow<List<FavoriteItem>> =
        movieRepository.getListOfFavoriteItem(category)

    override fun isFavoriteItem(movieId: Int): Flow<Boolean> =
        movieRepository.isFavoriteItem(movieId).flowOn(Dispatchers.IO)
}
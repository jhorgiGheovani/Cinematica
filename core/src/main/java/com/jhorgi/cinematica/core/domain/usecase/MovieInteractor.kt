package com.jhorgi.cinematica.core.domain.usecase

import androidx.paging.PagingData
import com.jhorgi.cinematica.core.data.Resource
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
    override fun getMovie(): Flow<PagingData<Movie>> = movieRepository.getMovie()
    override fun discoverMovie(): Flow<Resource<List<Movie>>> = movieRepository.discoverMovie()
    override fun getDiscoverMovieWithPaging(): Flow<PagingData<Movie>> = movieRepository.getDiscoverMovieWithPaging()

    override fun discoverTvShow(): Flow<Resource<List<TvSeries>>> = movieRepository.discoverTvShow()
    override fun getDiscoverTvSeriesWithPaging(): Flow<PagingData<TvSeries>> = movieRepository.getDiscoverTvSeriesWithPaging()

    override fun getMovieDetails(movie_id: Int): Flow<MovieDetails> =
        movieRepository.getMovieDetails(movie_id)

    override fun getTvSeriesDetails(series_id: Int): Flow<TvSeriesDetails> =
        movieRepository.getTvSeriesDetails(series_id)

    override fun getMovieCast(movie_id: Int): Flow<List<Credit>> =
        movieRepository.getMovieCast(movie_id).flowOn(Dispatchers.IO)

    override fun getMovieCrew(movie_id: Int): Flow<List<Credit>> =
        movieRepository.getMovieCrew(movie_id).flowOn(Dispatchers.IO)

    override fun getTvCast(series_id: Int): Flow<List<Credit>> =
        movieRepository.getTvCast(series_id)

    override fun getTvCrew(series_id: Int): Flow<List<Credit>> =
        movieRepository.getTvCrew(series_id)

    override suspend fun addItemToFavorite(movie: FavoriteItem, timeStamp: Long, category: String) =
        movieRepository.addMovieToFavorite(movie, timeStamp, category)

    override suspend fun deleteMovieFromFavorite(movie_id: Int) =
        movieRepository.deleteMovieFromFavorite(movie_id)

    override fun getListOfFavoriteItem(category: String): Flow<List<FavoriteItem>> =
        movieRepository.getListOfFavoriteItem(category)

    override fun isFavoriteItem(movieId: Int): Flow<Boolean> =
        movieRepository.isFavoriteItem(movieId).flowOn(Dispatchers.IO)
}
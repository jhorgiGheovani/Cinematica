package com.jhorgi.cinematica.seeAllPages

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.jhorgi.cinematica.core.data.pagingDataSource.MoviePagingSource.Companion.ARG_DISCOVER_MOVIE
import com.jhorgi.cinematica.core.data.pagingDataSource.MoviePagingSource.Companion.ARG_NOW_PLAYING_MOVIE
import com.jhorgi.cinematica.core.data.pagingDataSource.MoviePagingSource.Companion.ARG_POPULAR_MOVIE
import com.jhorgi.cinematica.core.data.pagingDataSource.MoviePagingSource.Companion.ARG_TOP_RATED_MOVIE
import com.jhorgi.cinematica.core.data.pagingDataSource.MoviePagingSource.Companion.ARG_UP_COMING_MOVIE
import com.jhorgi.cinematica.core.data.pagingDataSource.TvSeriesPagingSource.Companion.ARG_DISCOVER_TV_SERIES
import com.jhorgi.cinematica.core.data.pagingDataSource.TvSeriesPagingSource.Companion.ARG_POPULAR_TV_SERIES
import com.jhorgi.cinematica.core.data.pagingDataSource.TvSeriesPagingSource.Companion.ARG_TOP_RATED_TV_SERIES
import com.jhorgi.cinematica.core.data.source.remote.NETWORK_PAGE_SIZE
import com.jhorgi.cinematica.core.domain.usecase.MovieUseCase

class SeeAllPagesViewModel(movieUseCase: MovieUseCase)  : ViewModel() {

    val discoverTvSeries = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { movieUseCase.tvSeriesPagingSource(ARG_DISCOVER_TV_SERIES) }

    ).flow

    val topRatedTvSeries = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { movieUseCase.tvSeriesPagingSource(ARG_TOP_RATED_TV_SERIES) }

    ).flow

    val discoverMovie = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { movieUseCase.moviePagingSource(ARG_DISCOVER_MOVIE) }

    ).flow

    val popularMovie = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { movieUseCase.moviePagingSource(ARG_POPULAR_MOVIE) }

    ).flow

    val upcomingMovie = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { movieUseCase.moviePagingSource(ARG_UP_COMING_MOVIE) }

    ).flow

    val getNowPlayingMovie = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { movieUseCase.moviePagingSource(ARG_NOW_PLAYING_MOVIE) }

    ).flow

    val topRatedMovie = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { movieUseCase.moviePagingSource(ARG_TOP_RATED_MOVIE) }

    ).flow

    val popularTvShow = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { movieUseCase.tvSeriesPagingSource(ARG_POPULAR_TV_SERIES) }

    ).flow

}
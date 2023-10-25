package com.jhorgi.cinematica.seeAllPages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jhorgi.cinematica.core.domain.usecase.MovieUseCase

class SeeAllPagesViewModel(movieUseCase: MovieUseCase)  : ViewModel() {

    val discoverMovie = movieUseCase.getDiscoverMovieWithPaging().cachedIn(viewModelScope)

    val discoverTvSeries = movieUseCase.getDiscoverTvSeriesWithPaging().cachedIn(viewModelScope)

    val topRatedTvSeries = movieUseCase.getTopRatedTvShowsWithPaging().cachedIn(viewModelScope)

    val popularMovie = movieUseCase.getPopularMovieWithPaging().cachedIn(viewModelScope)

    val popularTvShow = movieUseCase.getPopularTvShowWithPaging().cachedIn(viewModelScope)

    val upcomingMovie = movieUseCase.getUpComingMovieWithPaging().cachedIn(viewModelScope)

    val topRatedMovie = movieUseCase.getTopRatedMovieWithPaging().cachedIn(viewModelScope)

    val getNowPlayingMovie = movieUseCase.getNowPlayingMovieWithPaging().cachedIn(viewModelScope)
}
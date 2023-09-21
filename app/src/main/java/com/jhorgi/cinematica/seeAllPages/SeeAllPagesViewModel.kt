package com.jhorgi.cinematica.seeAllPages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jhorgi.cinematica.core.domain.usecase.MovieUseCase

class SeeAllPagesViewModel(movieUseCase: MovieUseCase)  : ViewModel() {

    val discoverMovie = movieUseCase.getDiscoverMovieWithPaging().cachedIn(viewModelScope)

    val discoverTvSeries = movieUseCase.getDiscoverTvSeriesWithPaging().cachedIn(viewModelScope)
}
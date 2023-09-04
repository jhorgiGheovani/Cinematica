package com.jhorgi.cinematica.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jhorgi.cinematica.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val movieWithPaging = movieUseCase.getMovie().cachedIn(viewModelScope)

}
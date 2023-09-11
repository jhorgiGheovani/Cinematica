package com.jhorgi.cinematica.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhorgi.cinematica.core.domain.model.FavoriteMovie
import com.jhorgi.cinematica.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {


    private val _favoriteMovie = MutableLiveData<List<FavoriteMovie>>()
    val favoriteMovie: LiveData<List<FavoriteMovie>> = _favoriteMovie


    fun getFavorite() {
        movieUseCase.getMovieListFromFavorite()
            .onEach {
                _favoriteMovie.value = it
            }
            .launchIn(viewModelScope)
    }

}
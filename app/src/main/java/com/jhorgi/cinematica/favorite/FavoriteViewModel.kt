package com.jhorgi.cinematica.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhorgi.cinematica.core.domain.model.FavoriteItem
import com.jhorgi.cinematica.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {


    private val _favoriteItem = MutableLiveData<List<FavoriteItem>>()
    val favoriteItem: LiveData<List<FavoriteItem>> = _favoriteItem


    fun getListOfFavoriteItem(category:String) {
        movieUseCase.getListOfFavoriteItem(category)
            .onEach {
                _favoriteItem.value = it
            }
            .launchIn(viewModelScope)
    }

}
package com.jhorgi.cinematica.searchPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach

class SearchPageViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _discoverMovie = MutableLiveData<Resource<List<Movie>>>()
    val discoverMovie: LiveData<Resource<List<Movie>>> = _discoverMovie

    private val _discoverTvList = MutableLiveData<Resource<List<TvSeries>>>()
    val discoverTvList: LiveData<Resource<List<TvSeries>>> = _discoverTvList


    val queryChannel = MutableStateFlow("")

    @FlowPreview
    @ExperimentalCoroutinesApi
    val searchResult = queryChannel
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            movieUseCase.movieSearch(it)
        }
        .asLiveData()

    fun getDiscoverMovieList() {
        movieUseCase.discoverMovie()
            .onEach { data ->
                when (data) {
                    is Resource.Success -> {
                        _discoverMovie.value = data
                    }

                    is Resource.Error -> {
                        _discoverMovie.value = data
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun getDiscoverTvList() {
        movieUseCase.discoverTvShow()
            .onEach { data ->
                when (data) {
                    is Resource.Success -> {
                        _discoverTvList.value = data
                    }

                    is Resource.Error -> {
                        _discoverTvList.value = data
                    }
                }

            }
            .launchIn(viewModelScope)
    }
}
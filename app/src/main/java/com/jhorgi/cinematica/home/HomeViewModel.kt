package com.jhorgi.cinematica.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhorgi.cinematica.core.data.Resource
import com.jhorgi.cinematica.core.domain.model.Movie
import com.jhorgi.cinematica.core.domain.model.TvSeries
import com.jhorgi.cinematica.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _popularMovie = MutableLiveData<Resource<List<Movie>>>()
    val popularMovie: LiveData<Resource<List<Movie>>> = _popularMovie

    private val _upComingMovie = MutableLiveData<Resource<List<Movie>>>()
    val upComingMovie: LiveData<Resource<List<Movie>>> = _upComingMovie

    private val _topRatedMovie = MutableLiveData<Resource<List<Movie>>>()
    val topRatedMovie: LiveData<Resource<List<Movie>>> = _topRatedMovie

    private val _nowPlayingMovie = MutableLiveData<Resource<List<Movie>>>()
    val nowPlayingMovie: LiveData<Resource<List<Movie>>> = _nowPlayingMovie

    private val _popularTvShow = MutableLiveData<Resource<List<TvSeries>>>()
    val popularTvShow: LiveData<Resource<List<TvSeries>>> = _popularTvShow

    private val _topRatedTvShow = MutableLiveData<Resource<List<TvSeries>>>()
    val topRatedTvShow: LiveData<Resource<List<TvSeries>>> = _topRatedTvShow
    fun getPopularMovie(){
        movieUseCase.getPopularMovie()
            .onEach { data->
                when(data) {
                    is Resource.Success->{
                        _popularMovie.value = data
                    }
                    is Resource.Error->{
                        _popularMovie.value = data
                    }
                }

            }
            .launchIn(viewModelScope)
    }

    fun getUpComingMovie(){
        movieUseCase.getUpComingMovie()
            .onEach {  data->
                when(data){
                    is Resource.Success->{
                        _upComingMovie.value = data
                    }
                    is Resource.Error->{
                        _upComingMovie.value = data
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun getTopRatedMovie(){
        movieUseCase.getTopRatedMovie()
            .onEach { data->
                when(data) {
                    is Resource.Success->{
                        _topRatedMovie.value = data
                    }
                    is Resource.Error->{
                        _topRatedMovie.value = data
                    }
                }

            }
            .launchIn(viewModelScope)
    }

    fun getNowPlayingMovie(){
        movieUseCase.getNowPlayingMovie()
            .onEach { data->
                when(data) {
                    is Resource.Success->{
                        _nowPlayingMovie.value = data
                    }
                    is Resource.Error->{
                        _nowPlayingMovie.value = data
                    }
                }

            }
            .launchIn(viewModelScope)
    }

    fun getPopularTvShow(){
        movieUseCase.getPopularTvShow()
            .onEach { data->
                when(data) {
                    is Resource.Success->{
                        _popularTvShow.value = data
                    }
                    is Resource.Error->{
                        _popularTvShow.value = data
                    }
                }

            }
            .launchIn(viewModelScope)
    }

    fun getTopRatedTvShows(){
        movieUseCase.getTopRatedTvShows()
            .onEach { data->
                when(data) {
                    is Resource.Success->{
                        _topRatedTvShow.value = data
                    }
                    is Resource.Error->{
                        _topRatedTvShow.value = data
                    }
                }

            }
            .launchIn(viewModelScope)
    }



}
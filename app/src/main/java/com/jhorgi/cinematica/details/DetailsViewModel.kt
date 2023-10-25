package com.jhorgi.cinematica.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.FavoriteItem
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.domain.model.TvSeriesDetails
import com.jhorgi.cinematica.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailsViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _castMovieData = MutableLiveData<List<Credit>>()
    val castMovieData: LiveData<List<Credit>> = _castMovieData

    private val _castTvData = MutableLiveData<List<Credit>>()
    val castTvData: LiveData<List<Credit>> = _castTvData

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails = _movieDetails

    private val _tvSeriesDetails = MutableLiveData<TvSeriesDetails>()
    val tvSeriesDetails = _tvSeriesDetails

    private val _crewMovieData = MutableLiveData<List<Credit>>()
    val crewMovieData: LiveData<List<Credit>> = _crewMovieData

    private val _crewTvData = MutableLiveData<List<Credit>>()
    val crewTvData: LiveData<List<Credit>> = _crewTvData

    private val _isFavoriteItem = MutableLiveData<Boolean>()
    val isFavoriteItem: LiveData<Boolean> = _isFavoriteItem

    fun getMovieCast(movie_id: Int) {
        movieUseCase.getMovieCast(movie_id)
            .onEach {
                _castMovieData.value = it
            }
            .launchIn(viewModelScope)
    }

    fun getMovieCrew(movie_id: Int) {
        movieUseCase.getMovieCrew(movie_id)
            .onEach {
                _crewMovieData.value = it
            }
            .launchIn(viewModelScope)
    }

    fun getTvCast(series_id: Int) {
        movieUseCase.getTvCast(series_id)
            .onEach {
                _castTvData.value = it
            }
            .launchIn(viewModelScope)
    }

    fun getTvCrew(series_id: Int) {
        movieUseCase.getTvCrew(series_id)
            .onEach {
                _crewTvData.value = it
            }
            .launchIn(viewModelScope)
    }

    fun getMovieDetails(movie_id: Int) {
        movieUseCase.getMovieDetails(movie_id)
            .onEach {
                _movieDetails.value = it
            }
            .launchIn(viewModelScope)
    }

    fun getTvSeriesDetails(series_id: Int) {
        movieUseCase.getTvSeriesDetails(series_id)
            .onEach {
                _tvSeriesDetails.value = it
            }
            .launchIn(viewModelScope)
    }

    fun addMovieToFavorite(movie: FavoriteItem, timeStamp: Long, category: String, rating: String) {
        viewModelScope.launch {
            movieUseCase.addItemToFavorite(movie, timeStamp, category, rating)
        }
    }

    fun deleteMovieFromFavorite(movie_id: Int) {
        viewModelScope.launch {
            movieUseCase.deleteMovieFromFavorite(movie_id)
        }
    }

    fun isFavoriteItem(id: Int) {
        movieUseCase.isFavoriteItem(id)
            .onEach {
                _isFavoriteItem.value = it
            }
            .launchIn(viewModelScope)

    }

}
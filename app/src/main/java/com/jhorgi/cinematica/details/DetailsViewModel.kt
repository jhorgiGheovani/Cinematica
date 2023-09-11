package com.jhorgi.cinematica.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhorgi.cinematica.core.domain.model.Credit
import com.jhorgi.cinematica.core.domain.model.MovieDetails
import com.jhorgi.cinematica.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailsViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _castData = MutableLiveData<List<Credit>>()
    val castData: LiveData<List<Credit>> = _castData

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails = _movieDetails

    private val _crewData = MutableLiveData<List<Credit>>()
    val crewItem: LiveData<List<Credit>> = _crewData

    private val _isFavoriteMovie = MutableLiveData<Boolean>()
    val isFavoriteMovie: LiveData<Boolean> = _isFavoriteMovie

    fun getMovieCast(movie_id: Int) {
        movieUseCase.getCast(movie_id)
            .onEach {
                _castData.value = it
            }
            .launchIn(viewModelScope)
    }

    fun getMovieCrew(movie_id: Int) {
        movieUseCase.getCrew(movie_id)
            .onEach {
                _crewData.value = it
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

    fun addMovieToFavorite(movie: MovieDetails, timeStamp: Long) {
        viewModelScope.launch {
            movieUseCase.addMovieToFavorite(movie, timeStamp)
        }
    }

    fun deleteMovieFromFavorite(movie_id: Int) {
        viewModelScope.launch {
            movieUseCase.deleteMovieFromFavorite(movie_id)
        }
    }

    fun isFavoriteMovie(movie_id: Int) {
        movieUseCase.isFavoriteMovie(movie_id)
            .onEach {
                _isFavoriteMovie.value = it
            }
            .launchIn(viewModelScope)

    }

}
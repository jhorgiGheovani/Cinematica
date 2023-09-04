package com.jhorgi.cinematica.di

import com.jhorgi.cinematica.core.domain.usecase.MovieInteractor
import com.jhorgi.cinematica.core.domain.usecase.MovieUseCase
import com.jhorgi.cinematica.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}
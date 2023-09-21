package com.jhorgi.cinematica.di

import com.jhorgi.cinematica.core.domain.usecase.MovieInteractor
import com.jhorgi.cinematica.core.domain.usecase.MovieUseCase
import com.jhorgi.cinematica.details.DetailsViewModel
import com.jhorgi.cinematica.favorite.FavoriteViewModel
import com.jhorgi.cinematica.home.HomeViewModel
import com.jhorgi.cinematica.searchPage.SearchPageViewModel
import com.jhorgi.cinematica.seeAllPages.SeeAllPagesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { SearchPageViewModel(get()) }
    viewModel {SeeAllPagesViewModel(get())}
}
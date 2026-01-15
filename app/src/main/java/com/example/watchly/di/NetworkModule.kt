package com.example.watchly.di

import com.example.watchly.presentation.details.DetailsViewModel
import com.example.watchly.presentation.home.HomeViewModel
import com.example.watchly.data.repository.Repository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { Repository() }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }

}
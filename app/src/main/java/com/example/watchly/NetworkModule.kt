package com.example.watchly

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.watchly.data.Repository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { Repository() }
    viewModel { HomeViewModel(get()) }
}
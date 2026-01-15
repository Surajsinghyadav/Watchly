package com.example.watchly

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchly.data.Repository
import com.example.watchly.data.Title
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {


    private val _movies = MutableStateFlow<List<Title>>(emptyList())
    private val _tvshows = MutableStateFlow<List<Title>>(emptyList())
    val movies : StateFlow<List<Title>> = _movies.asStateFlow()
    val tvshows = _tvshows.asStateFlow()

    init {
        getMovies()
//        getTvShows()
    }

    fun getMovies() {
        viewModelScope.launch {
            try {
                val result = repository.getMovies()
                _movies.value = result.titles
            } catch (e: Exception) {

            }
        }
    }

    fun getTvShows() {
        viewModelScope.launch {
            try {
                val result = repository.getTvShows()
                _tvshows.value = result.titles
            }catch (e: Exception){

            }
        }
    }
}

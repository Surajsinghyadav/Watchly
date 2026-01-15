package com.example.watchly

import androidx.lifecycle.ViewModel
import com.example.watchly.data.Repository
import com.example.watchly.data.Title
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(private val repository: Repository) : ViewModel() {


    val disposable = CompositeDisposable()
    private val _movies = MutableStateFlow<List<Title>>(emptyList())
    private val _tvshows = MutableStateFlow<List<Title>>(emptyList())
    private val _error = MutableStateFlow<String?>(null)
    private val _loading = MutableStateFlow(false )
    val movies = _movies.asStateFlow()
    val tvshows = _tvshows.asStateFlow()
    val error = _error.asStateFlow()
    val loading = _loading.asStateFlow()

    init {
        loadHomeData()
    }

    fun loadHomeData(){
        _loading.value = true
        disposable.add(
            repository.getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { result ->
                    _movies.value = result.first
                    _tvshows.value = result.second
                    _loading.value = false
                }, { error ->
                    _error.value = error.message
                    _loading.value = false
                }
                )
        )
    }


    override fun onCleared() {
       disposable.clear()
        super.onCleared()
    }

}

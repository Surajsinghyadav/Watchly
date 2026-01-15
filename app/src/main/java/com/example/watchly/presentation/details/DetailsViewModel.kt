package com.example.watchly.presentation.details

import androidx.lifecycle.ViewModel
import com.example.watchly.data.model.TitleDetailsResponse
import com.example.watchly.data.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailsViewModel(private val repo: Repository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _details = MutableStateFlow<TitleDetailsResponse?>(null)
    private val _loading = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)

    val details = _details.asStateFlow()
    val loading = _loading.asStateFlow()
    val error = _error.asStateFlow()

    fun loadDetails(id: Int) {
        _loading.value = true
        disposables.add(
            repo.getTitleDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _details.value = result
                    _loading.value = false
                }, { e ->
                    _error.value = e.message
                    _loading.value = false
                })
        )
    }

    override fun onCleared() {
        disposables.clear()
    }
}
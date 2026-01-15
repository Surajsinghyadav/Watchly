package com.example.watchly.data.repository

import com.example.watchly.data.model.Title
import com.example.watchly.data.model.TitleDetailsResponse
import com.example.watchly.data.remote.RetrofitInstance
import io.reactivex.rxjava3.core.Single

class Repository {

    fun getHomeData(): Single<Pair<List<Title>, List<Title>>, > {
        val moviesSingle = RetrofitInstance.api.getMovies()
        val tvShowsSingle = RetrofitInstance.api.getTvShows()

        return Single.zip(moviesSingle, tvShowsSingle){ movies, shows ->
            Pair(movies.titles, shows.titles)

        }
    }

    fun getTitleDetails(id: Int): Single<TitleDetailsResponse> {
        return RetrofitInstance.api.getTitleDetails(id)
    }

}
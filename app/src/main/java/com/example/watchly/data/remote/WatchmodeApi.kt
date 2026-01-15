package com.example.watchly.data.remote

import com.example.watchly.data.model.DataModule
import com.example.watchly.data.model.TitleDetailsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface WatchmodeApi {
    @Headers("x-rapidapi-host: watchmode.p.rapidapi.com")

    @GET("list-titles")
    fun getMovies(
        @Query("types") type: String = "movie",
        @Query("page") page: Int = 1,
        @Query("sort_by") sort: String = "popularity_desc",
    ) : Single<DataModule>


    @GET("list-titles")
    fun getTvShows(
        @Query("types") type: String = "tv_series",
        @Query("page") page: Int = 1,
        @Query("sort_by") sort: String = "popularity_desc",
    ) : Single<DataModule>

    @GET("title/{id}/details")
    fun getTitleDetails(
        @Path("id") id: Int,
        @Query("language") lang: String = "EN"
    ): Single<TitleDetailsResponse>






}
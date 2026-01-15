package com.example.watchly.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WatchmodeApi {
    @Headers("x-rapidapi-host: watchmode.p.rapidapi.com")

    @GET("list-titles")
    suspend fun getMovies(
        @Query("types") type: String = "movie",
        @Query("page") page: Int = 1,
        @Query("sort_by") sort: String = "popularity_desc",
    ) : Response<DataModule>


    @GET("list-titles")
    suspend fun getTvShows(
        @Query("types") type: String = "tv_series",
        @Query("page") page: Int = 1,
        @Query("sort_by") sort: String = "popularity_desc",
    ) : Response<DataModule>


}
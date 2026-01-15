package com.example.watchly.data

class Repository {
    suspend fun getMovies() : DataModule{
        val response = RetrofitInstance.api.getMovies()
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            throw Exception("Failed to fetch movies")
        }
    }

    suspend fun  getTvShows() : DataModule{
        val response = RetrofitInstance.api.getTvShows()
        if (response.isSuccessful && response.body() != null){
            return response.body()!!
        } else {
            throw Exception("Failed to fetch tv shows")
        }
    }
}
package com.example.watchly.data

import com.example.watchly.data.RetrofitInstance.Client
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://watchmode.p.rapidapi.com/"
    private const val API_KEY = "b1c70f9676msh6eefd393576a6cap110c63jsn73aa644130e1"

    private val Client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-rapidapi-key", API_KEY)
                .build()
            chain.proceed(request)
        }
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()


    val api: WatchmodeApi by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(Client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(WatchmodeApi::class.java)

    }

}
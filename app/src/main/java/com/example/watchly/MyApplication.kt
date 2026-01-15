package com.example.watchly

import android.app.Application
import com.example.watchly.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(networkModule)
        }
    }
}
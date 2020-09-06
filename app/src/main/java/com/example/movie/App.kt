package com.example.movie

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.example.movie.di.module.appModule
import com.example.movie.di.module.repoModule
import com.example.movie.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}
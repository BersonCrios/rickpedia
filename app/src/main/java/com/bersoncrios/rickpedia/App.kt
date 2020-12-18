package com.bersoncrios.rickpedia

import android.app.Application
import com.bersoncrios.rickpedia.di.DependencyModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(DependencyModule.modules)
        }
    }
}
package com.example.neglectapp

import android.app.Application
import android.content.Context
import com.example.neglectapp.di.dependenciesAppModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

@HiltAndroidApp
class MainApplication : Application(), KoinComponent {
    companion object {
        private var instance: MainApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // use Koin logger
            printLogger()
            // declare modules
            modules(dependenciesAppModule)
        }
    }
}

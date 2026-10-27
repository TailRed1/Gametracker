package com.example.tymkov.gametracker

import android.app.Application
import com.example.tymkov.gametracker.data.AppContainer

class GameTrackerApplication : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        container = AppContainer(
            context = applicationContext
        )
    }
}
package com.alexzh.moodtracker

import android.app.Application
import com.alexzh.moodtracker.di.appModule
import com.alexzh.moodtracker.di.dataModule
import kotlinx.coroutines.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MoodTrackerApp : Application() {

    companion object {
        var applicationScope = MainScope()
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        applicationScope.cancel("onLowMemory called by system")
        applicationScope = MainScope()
    }

    private fun initDI() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MoodTrackerApp)
            modules(listOf(appModule, dataModule))
        }
    }
}
package org.soma.weatherviewer.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SWVApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
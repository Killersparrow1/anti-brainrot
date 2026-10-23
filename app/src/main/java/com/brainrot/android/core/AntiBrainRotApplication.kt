package com.brainrot.android.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AntiBrainRotApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize libraries, analytics, crashlytics, etc.
    }
}
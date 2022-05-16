package com.kenshi.animereview

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AnimeReviewApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initMode()
    }

    private fun initMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
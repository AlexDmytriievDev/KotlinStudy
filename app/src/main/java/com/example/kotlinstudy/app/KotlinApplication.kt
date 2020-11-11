package com.example.kotlinstudy.app

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.kotlinstudy.R
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class KotlinApplication : Application(), LifecycleObserver {

    var appState: State = State.UNDEFINED
        private set

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onForeground() {
        Timber.d(getString(R.string.foreground_state))
        appState = State.FOREGROUND
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onBackground() {
        Timber.d(getString(R.string.background_state))
        appState = State.BACKGROUND
    }

    enum class State(val value: Int) {

        UNDEFINED(0),

        FOREGROUND(1),

        BACKGROUND(2)
    }
}
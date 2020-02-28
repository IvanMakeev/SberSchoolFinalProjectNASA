package com.example.presentation

import android.app.Application
import com.example.presentation.di.AppComponent
import com.example.presentation.di.AppModule
import com.example.presentation.di.DaggerAppComponent

class AppDelegate : Application() {

    companion object {
        private lateinit var INSTANCE: AppDelegate
        @JvmStatic
        val injector: AppDelegate
            get() = INSTANCE
    }

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(INSTANCE))
                .build()
    }

    fun getAppComponent(): AppComponent = appComponent
}
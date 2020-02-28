package com.example.presentation.di

import com.example.presentation.ui.fragment.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, InteractorModule::class])
interface AppComponent {
    fun inject(injector: MainFragment)
}
package com.example.nasa.presentation.di;

import com.example.nasa.presentation.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, RepositoryModule.class, ServiceModule.class})
public interface AppComponent {

    void inject(MainActivity injector);
}

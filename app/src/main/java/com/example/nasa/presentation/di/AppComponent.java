package com.example.nasa.presentation.di;

import com.example.nasa.presentation.ui.MainActivity;
import com.example.nasa.presentation.ui.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,
        NetworkModule.class,
        RepositoryModule.class,
        ServiceModule.class,
        MapperModule.class})
public interface AppComponent {

    void inject(MainFragment injector);
}

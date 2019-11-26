package com.example.presentation.di;

import com.example.presentation.ui.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,
        NetworkModule.class,
        RepositoryModule.class,
        InteractorModule.class,
        MapperModule.class})
public interface AppComponent {

    void inject(MainFragment injector);
}

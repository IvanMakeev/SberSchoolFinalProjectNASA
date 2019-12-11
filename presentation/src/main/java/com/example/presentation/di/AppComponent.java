package com.example.presentation.di;

import com.example.presentation.ui.fragment.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,
        NetworkModule.class,
        InteractorModule.class})
public interface AppComponent {

    void inject(MainFragment injector);
}

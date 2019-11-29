package com.example.presentation;

import android.app.Application;

import com.example.presentation.di.AppComponent;
import com.example.presentation.di.AppModule;
import com.example.presentation.di.DaggerAppComponent;

import org.jetbrains.annotations.NotNull;

public final class AppDelegate extends Application {

    private static AppDelegate INSTANCE;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(INSTANCE))
                .build();
    }

    @NotNull
    public static AppDelegate getInjector() {
        return INSTANCE;
    }

    @NotNull
    public AppComponent getAppComponent() {
        return appComponent;
    }
}

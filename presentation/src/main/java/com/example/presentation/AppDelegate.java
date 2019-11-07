package com.example.presentation;

import android.app.Application;

import com.example.presentation.di.AppComponent;
import com.example.presentation.di.AppModule;
import com.example.presentation.di.DaggerAppComponent;

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

    public static AppDelegate getInjector() {
        return INSTANCE;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

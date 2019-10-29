package com.example.nasa;

import android.app.Application;

import com.example.nasa.presentation.di.AppComponent;
import com.example.nasa.presentation.di.AppModule;
import com.example.nasa.presentation.di.DaggerAppComponent;

public class AppDelegate extends Application {

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

    public static AppDelegate getInjector(){
        return INSTANCE;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

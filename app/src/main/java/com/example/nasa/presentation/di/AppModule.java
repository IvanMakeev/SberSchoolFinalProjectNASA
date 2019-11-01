package com.example.nasa.presentation.di;

import androidx.room.Room;

import com.example.nasa.AppDelegate;
import com.example.nasa.data.database.NasaDao;
import com.example.nasa.data.database.NasaDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final AppDelegate mAppDelegate;

    public AppModule(AppDelegate appDelegate) {
        this.mAppDelegate = appDelegate;
    }

    @Provides
    @Singleton
    public AppDelegate provideApp() {
        return mAppDelegate;
    }

    @Provides
    @Singleton
    public NasaDatabase provideDatabase() {
        return Room.databaseBuilder(
                mAppDelegate,
                NasaDatabase.class,
                "nasa_database"
        )
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public NasaDao ProvideNasaDao(NasaDatabase database) {
        return database.getNasaDao();
    }
}

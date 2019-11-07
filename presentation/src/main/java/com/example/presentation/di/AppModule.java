package com.example.presentation.di;

import androidx.room.Room;

import com.example.presentation.AppDelegate;
import com.example.data.database.NasaDao;
import com.example.data.database.NasaDatabase;

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
    AppDelegate provideApp() {
        return mAppDelegate;
    }

    @Provides
    @Singleton
    NasaDatabase provideDatabase() {
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
    NasaDao ProvideNasaDao(NasaDatabase database) {
        return database.getNasaDao();
    }
}

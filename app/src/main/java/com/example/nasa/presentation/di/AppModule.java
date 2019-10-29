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

    private final AppDelegate appDelegate;

    public AppModule(AppDelegate appDelegate) {
        this.appDelegate = appDelegate;
    }

    @Provides
    @Singleton
    public AppDelegate provideApp() {
        return appDelegate;
    }

    @Provides
    @Singleton
    public NasaDatabase provideDatabase() {
        return Room.databaseBuilder(
                appDelegate,
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

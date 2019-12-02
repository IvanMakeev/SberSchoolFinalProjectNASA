package com.example.presentation.di;

import androidx.room.Room;

import com.example.presentation.AppDelegate;
import com.example.data.database.NasaDao;
import com.example.data.database.NasaDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль внедренения зависимостей для работы с базой данных
 */
@Module
public class AppModule {

    private final AppDelegate mAppDelegate;

    /**
     * @param appDelegate экземпляр Applicaton класса
     */
    public AppModule(AppDelegate appDelegate) {
        this.mAppDelegate = appDelegate;
    }

    /**
     * Используется для предоставления зависимости Applicaton context
     *
     * @return возвращает Applicaton context
     */
    @Provides
    @Singleton
    AppDelegate provideApp() {
        return mAppDelegate;
    }

    /**
     * Используется для настройки базы данных
     *
     * @return возвращает экземпляр базы данных
     */
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

    /**
     * Используется для получения DAO
     *
     * @param database передается экземпляр базы данных
     * @return возвращает экземпляр DAO
     */
    @Provides
    @Singleton
    NasaDao ProvideNasaDao(NasaDatabase database) {
        return database.getNasaDao();
    }
}

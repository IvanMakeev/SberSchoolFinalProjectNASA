package com.example.presentation.di

import androidx.room.Room
import com.example.data.database.NasaDatabase
import com.example.presentation.AppDelegate
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Модуль внедренения зависимостей для работы с базой данных
 */
@Module
class AppModule
/**
 * @constructor appDelegate экземпляр Applicaton класса
 */
(private val mAppDelegate: AppDelegate) {
    /**
     * Используется для предоставления зависимости Applicaton context
     *
     * @return возвращает Applicaton context
     */
    @Provides
    @Singleton
    fun provideApp(): AppDelegate = mAppDelegate

    /**
     * Используется для настройки базы данных
     *
     * @return возвращает экземпляр базы данных
     */
    @Provides
    @Singleton
    fun provideDatabase() = Room.databaseBuilder(
            mAppDelegate,
            NasaDatabase::class.java,
            "nasa_database"
    )
            .fallbackToDestructiveMigration()
            .build()

    /**
     * Используется для получения DAO
     *
     * @param database передается экземпляр базы данных
     * @return возвращает экземпляр DAO
     */
    @Provides
    @Singleton
    fun provideNasaDao(database: NasaDatabase) = database.nasaDao
}
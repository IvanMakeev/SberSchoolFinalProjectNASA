package com.example.presentation.di

import com.example.data.BuildConfig
import com.example.data.api.ApiKeyInterceptor
import com.example.data.api.NasaApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Модуль внедренения зависимостей для работы с сетью
 */
@Module
internal class NetworkModule {
    /**
     * @return возвращает экземпляр Gson
     */
    @Provides
    @Singleton
    fun provideGson() = Gson()


    /**
     * @return возвращает экземпляр OkHttpClient
     */
    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(ApiKeyInterceptor())
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }
        return builder.build()
    }

    /**
     * @return возвращает экземпляр Retrofit
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()


    /**
     * @return возвращает экземпляр NasaApi для работы с web api
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NasaApi = retrofit.create(NasaApi::class.java)

}
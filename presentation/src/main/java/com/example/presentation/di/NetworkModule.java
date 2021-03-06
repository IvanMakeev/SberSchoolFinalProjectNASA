package com.example.presentation.di;

import com.example.data.api.ApiKeyInterceptor;
import com.example.data.api.NasaApi;
import com.google.gson.Gson;

import javax.inject.Singleton;

import com.example.data.BuildConfig;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Модуль внедренения зависимостей для работы с сетью
 */
@Module
class NetworkModule {

    /**
     * @return возвращает экземпляр Gson
     */
    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    /**
     * @return возвращает экземпляр OkHttpClient
     */
    @Provides
    @Singleton
    OkHttpClient provideClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new ApiKeyInterceptor());
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.level(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        return builder.build();
    }

    /**
     * @return возвращает экземпляр Retrofit
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * @return возвращает экземпляр NasaApi для работы с web api
     */
    @Provides
    @Singleton
    NasaApi provideApiService(Retrofit retrofit) {
        return retrofit.create(NasaApi.class);
    }
}

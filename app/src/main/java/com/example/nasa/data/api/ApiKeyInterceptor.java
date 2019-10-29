package com.example.nasa.data.api;

import androidx.annotation.NonNull;

import com.example.nasa.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {
    @Override
    public @NonNull
    Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl httpUrl = request
                .url()
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build();
        return chain.proceed(request
                .newBuilder()
                .url(httpUrl)
                .build());
    }
}

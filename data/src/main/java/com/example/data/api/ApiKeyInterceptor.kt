package com.example.data.api

import com.example.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiKeyInterceptor : Interceptor {

    companion object {
        private const val PROPERTY_API_KEY = "api_key"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val httpUrl = request
                .url
                .newBuilder()
                .addQueryParameter(PROPERTY_API_KEY, BuildConfig.API_KEY)
                .build()
        return chain.proceed(request
                .newBuilder()
                .url(httpUrl)
                .build())
    }
}
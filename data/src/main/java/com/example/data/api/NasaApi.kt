package com.example.data.api

import com.example.data.model.APODJson
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    @GET("planetary/apod")
    fun getAstronomyPicture(@Query("date") date: String): Single<APODJson>
}
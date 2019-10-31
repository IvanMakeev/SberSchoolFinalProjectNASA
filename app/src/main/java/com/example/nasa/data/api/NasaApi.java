package com.example.nasa.data.api;

import com.example.nasa.data.model.APODJson;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NasaApi {

    @GET("planetary/apod")
    Single<APODJson> getAstronomyPicture(@Query("date") String date);
}

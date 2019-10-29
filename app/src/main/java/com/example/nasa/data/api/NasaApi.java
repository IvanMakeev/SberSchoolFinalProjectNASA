package com.example.nasa.data.api;

import com.example.nasa.data.model.APODJson;
import com.example.nasa.domain.model.APOD;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NasaApi {

    @GET("planetary/apod")
    public Single<APODJson> getAstronomyPicture(@Query("date") String date);
}

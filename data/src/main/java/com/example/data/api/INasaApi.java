package com.example.data.api;

import com.example.data.model.APODJson;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface INasaApi {

    @GET("planetary/apod")
    Single<APODJson> getAstronomyPicture(@Query("date") String date);
}

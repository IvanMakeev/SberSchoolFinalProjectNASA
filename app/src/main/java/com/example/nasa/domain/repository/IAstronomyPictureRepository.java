package com.example.nasa.domain.repository;

import com.example.nasa.data.model.APODJson;
import com.example.nasa.domain.model.APOD;

import io.reactivex.Single;

public interface IAstronomyPictureRepository {

    public static final String SERVER = "server";
    public static final String DB = "db";

    public Single<APODJson> getAstronomyPicture(String date);

    public void insertAstronomyPicture(APODJson apod);
}

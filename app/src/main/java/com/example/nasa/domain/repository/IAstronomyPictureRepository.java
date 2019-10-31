package com.example.nasa.domain.repository;

import com.example.nasa.domain.model.APODEntity;

import io.reactivex.Single;

public interface IAstronomyPictureRepository {

    String SERVER = "server";
    String DB = "db";

    Single<APODEntity> getAstronomyPicture(String date);

    void insertAstronomyPicture(APODEntity apod);
}

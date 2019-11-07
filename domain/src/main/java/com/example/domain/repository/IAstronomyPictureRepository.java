package com.example.domain.repository;

import com.example.domain.model.APODEntity;

import io.reactivex.Single;

public interface IAstronomyPictureRepository {

    String SERVER = "server";
    String DB = "db";

    Single<APODEntity> getAstronomyPicture(String date);

    void insertAstronomyPicture(APODEntity apod);
}

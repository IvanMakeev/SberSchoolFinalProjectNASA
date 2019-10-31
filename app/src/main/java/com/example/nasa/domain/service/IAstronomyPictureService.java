package com.example.nasa.domain.service;

import com.example.nasa.domain.model.APODEntity;

import io.reactivex.Single;

public interface IAstronomyPictureService {

    Single<APODEntity> getAstronomyPicture(String date);

    void insertAstronomyPicture(APODEntity apod);
}

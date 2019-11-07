package com.example.domain.service;

import com.example.domain.model.APODEntity;

import io.reactivex.Single;

public interface IAstronomyPictureService {

    Single<APODEntity> getAstronomyPicture(String date);

    void insertAstronomyPicture(APODEntity apod);
}

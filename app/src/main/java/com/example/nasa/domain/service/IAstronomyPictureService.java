package com.example.nasa.domain.service;

import com.example.nasa.data.model.APODJson;

import io.reactivex.Single;

public interface IAstronomyPictureService {

    public Single<APODJson> getAstronomyPicture(String date);

    public void insertAstronomyPicture(APODJson apod);
}

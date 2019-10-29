package com.example.nasa.data.repository;

import com.example.nasa.data.api.NasaApi;
import com.example.nasa.data.model.APODJson;
import com.example.nasa.domain.repository.IAstronomyPictureRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class AstronomyPictureServerRepository implements IAstronomyPictureRepository {

    @Inject
    NasaApi mApi;

    @Inject
    public AstronomyPictureServerRepository() {
    }

    @Override
    public Single<APODJson> getAstronomyPicture(String date) {
        return mApi.getAstronomyPicture(date);
    }

    @Override
    public void insertAstronomyPicture(APODJson apod) {
        //do nothing
    }
}

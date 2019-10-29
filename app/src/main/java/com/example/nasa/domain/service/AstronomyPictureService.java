package com.example.nasa.domain.service;

import com.example.nasa.data.model.APODJson;
import com.example.nasa.domain.repository.IAstronomyPictureRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class AstronomyPictureService implements IAstronomyPictureService {

    @Inject
    @Named(IAstronomyPictureRepository.SERVER)
    public IAstronomyPictureRepository serverRepository;

    @Inject
    @Named(IAstronomyPictureRepository.DB)
    public IAstronomyPictureRepository dbRepository;

    @Inject
    public AstronomyPictureService() {
    }

    @Override
    public Single<APODJson> getAstronomyPicture(String date) {
        return serverRepository.getAstronomyPicture(date)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(dbRepository::insertAstronomyPicture)
                .onErrorReturn(
                        throwable -> dbRepository.getAstronomyPicture(date).blockingGet()
                );
    }

    @Override
    public void insertAstronomyPicture(APODJson apod) {
        dbRepository.insertAstronomyPicture(apod);
    }
}

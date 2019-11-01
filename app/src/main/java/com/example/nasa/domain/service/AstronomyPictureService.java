package com.example.nasa.domain.service;

import com.example.nasa.domain.model.APODEntity;
import com.example.nasa.domain.repository.IAstronomyPictureRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class AstronomyPictureService implements IAstronomyPictureService {

    //перевести инжект через конструктор


    @Inject
    @Named(IAstronomyPictureRepository.SERVER)
    IAstronomyPictureRepository serverRepository;

    @Inject
    @Named(IAstronomyPictureRepository.DB)
    IAstronomyPictureRepository dbRepository;

    @Inject
    AstronomyPictureService() {
    }

    @Override
    public Single<APODEntity> getAstronomyPicture(String date) {
        return dbRepository.getAstronomyPicture(date)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(
                        throwable -> {
                            if (throwable instanceof NullPointerException) {
                                return serverRepository.getAstronomyPicture(date)
                                        .doOnSuccess(dbRepository::insertAstronomyPicture)
                                        .blockingGet();
                            } else {
                                return null;
                            }
                        }
                );
    }

    @Override
    public void insertAstronomyPicture(APODEntity apod) {
        dbRepository.insertAstronomyPicture(apod);
    }
}

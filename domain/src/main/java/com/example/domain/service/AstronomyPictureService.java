package com.example.domain.service;

import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;


import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public final class AstronomyPictureService implements IAstronomyPictureService {

    private final IAstronomyPictureRepository serverRepository;
    private final IAstronomyPictureRepository dbRepository;

    public AstronomyPictureService(IAstronomyPictureRepository serverRepository, IAstronomyPictureRepository dbRepository) {
        this.serverRepository = serverRepository;
        this.dbRepository = dbRepository;
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

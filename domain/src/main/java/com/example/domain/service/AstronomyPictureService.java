package com.example.domain.service;

import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;


import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
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
                .flatMap(getSource(date))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void insertAstronomyPicture(APODEntity apod) {
        dbRepository.insertAstronomyPicture(apod);
    }

    @NotNull
    private Function<APODEntity, SingleSource<APODEntity>> getSource(String date) {
        return apodEntity -> {
            if (isDataExist(apodEntity)) {
                return extractFromDatabase(apodEntity);
            } else {
                return extractFromNetwork(date);
            }
        };
    }

    private boolean isDataExist(APODEntity apodEntity) {
        return !apodEntity.getDate().equals("");
    }

    @NotNull
    private SingleSource<APODEntity> extractFromDatabase(APODEntity apodEntity) {
        return Single.fromCallable(() -> apodEntity);
    }

    @NotNull
    private SingleSource<APODEntity> extractFromNetwork(String date) {
        return serverRepository.getAstronomyPicture(date)
                .doOnSuccess(dbRepository::insertAstronomyPicture);
    }
}

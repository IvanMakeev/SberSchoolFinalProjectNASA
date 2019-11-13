package com.example.domain.service;

import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;


import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public final class AstronomyPictureService implements IAstronomyPictureService {

    private final IAstronomyPictureRepository networkRepository;
    private final IAstronomyPictureRepository dbRepository;

    public AstronomyPictureService(IAstronomyPictureRepository networkRepository, IAstronomyPictureRepository dbRepository) {
        this.networkRepository = networkRepository;
        this.dbRepository = dbRepository;
    }

    @NotNull
    @Override
    public Single<APODEntity> getAstronomyPicture(@NotNull String date) {
        return dbRepository.getAstronomyPicture(date)
                .flatMap(getSource(date))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void insertAstronomyPicture(@NotNull APODEntity apod) {
        dbRepository.insertAstronomyPicture(apod);
    }

    @NotNull
    private Function<APODEntity, SingleSource<APODEntity>> getSource(@NotNull String date) {
        return apodEntity -> {
            if (isDataExist(apodEntity)) {
                return extractFromDatabase(apodEntity);
            } else {
                return extractFromNetwork(date);
            }
        };
    }

    private boolean isDataExist(@NotNull APODEntity apodEntity) {
        return !apodEntity.getDate().equals("");
    }

    @NotNull
    private SingleSource<APODEntity> extractFromDatabase(@NotNull APODEntity apodEntity) {
        return Single.fromCallable(() -> apodEntity);
    }

    @NotNull
    private SingleSource<APODEntity> extractFromNetwork(@NotNull String date) {
        return networkRepository.getAstronomyPicture(date)
                .doOnSuccess(dbRepository::insertAstronomyPicture);
    }
}

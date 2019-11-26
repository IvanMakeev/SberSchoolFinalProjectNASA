package com.example.domain.interactor;

import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;


public class AstronomyPictureInteractor implements IAstronomyPictureInteractor {

    @NotNull
    private final IAstronomyPictureRepository mRepository;

    public AstronomyPictureInteractor(@NotNull IAstronomyPictureRepository repository) {
        mRepository = repository;
    }

    @NotNull
    @Override
    public Single<APODEntity> getAstronomyPicture(@NotNull String date) {
        return mRepository.getAstronomyPicture(date);
    }

    @Override
    public void insertAstronomyPicture(@NotNull APODEntity apod) {
        mRepository.insertAstronomyPicture(apod);
    }
}

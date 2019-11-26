package com.example.domain.interactor;

import com.example.domain.model.APODEntity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;

public interface IAstronomyPictureInteractor {

    @NotNull
    Single<APODEntity> getAstronomyPicture(@NotNull String date);

    void insertAstronomyPicture(@NotNull APODEntity apod);
}

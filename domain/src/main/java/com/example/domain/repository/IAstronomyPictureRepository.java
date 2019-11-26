package com.example.domain.repository;

import com.example.domain.model.APODEntity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;

public interface IAstronomyPictureRepository {

    @NotNull
    Single<APODEntity> getAstronomyPicture(@NotNull String date);

    void insertAstronomyPicture(@NotNull APODEntity apod);
}

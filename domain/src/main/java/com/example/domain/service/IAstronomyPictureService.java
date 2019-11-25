package com.example.domain.service;

import com.example.domain.model.APODEntity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;

public interface IAstronomyPictureService {

    @NotNull
    Single<APODEntity> getAstronomyPicture(@NotNull String date);

    void insertAstronomyPicture(@NotNull APODEntity apod);
}

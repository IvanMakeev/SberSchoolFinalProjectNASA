package com.example.domain.interactor;

import com.example.domain.exception.CommonException;
import com.example.domain.exception.NetworkAccessException;
import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;

public class AstronomyPictureInteractor implements IAstronomyPictureInteractor {

    @NotNull
    private final IAstronomyPictureRepository mRepository;

    private static List<Class<? extends IOException>> networkErrorList = Arrays.asList(
            UnknownHostException.class,
            SocketTimeoutException.class,
            ConnectException.class
    );

    public AstronomyPictureInteractor(@NotNull IAstronomyPictureRepository repository) {
        mRepository = repository;
    }

    @NotNull
    @Override
    public Single<APODEntity> getAstronomyPicture(@NotNull String date) {
        return mRepository.getAstronomyPicture(date)
                .onErrorResumeNext(throwable -> {
                    if (networkErrorList.contains(((Exception) throwable).getClass())) {
                        return Single.error(new NetworkAccessException(throwable));
                    }
                    return Single.error(new CommonException(throwable));
                });
    }

    @Override
    public void insertAstronomyPicture(@NotNull APODEntity apod) {
        mRepository.insertAstronomyPicture(apod);
    }
}

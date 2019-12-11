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

/**
 * Реализация интерактора, отвечающего за операции связанные с получением и сохранением данных
 */
public class AstronomyPictureInteractor implements IAstronomyPictureInteractor {

    @NotNull
    private final IAstronomyPictureRepository mRepository;

    /**
     * Список содержащий в себе классы с сетевыми исключениями
     */
    private final static List<Class<? extends IOException>> networkErrorList = Arrays.asList(
            UnknownHostException.class,
            SocketTimeoutException.class,
            ConnectException.class
    );

    public AstronomyPictureInteractor(@NotNull IAstronomyPictureRepository repository) {
        mRepository = repository;
    }

    /**
     * Получение данных
     *
     * @param date дата для которой необходимо получить данные
     * @return возвращает Single с данными для отображения пользователю
     */
    @NotNull
    @Override
    public Single<APODEntity> getAstronomyPicture(@NotNull String date) {
        return mRepository.getAstronomyPicture(date)
                .onErrorResumeNext(throwable -> {
                    boolean isError = networkErrorList.contains(((Exception) throwable).getClass());
                    Exception exception = isError ?
                            new NetworkAccessException(throwable) :
                            new CommonException(throwable);
                    return Single.error(exception);
                });
    }

    /**
     * Сохранение данных
     *
     * @param apod pojo объект для сохранения данных
     */
    @Override
    public void insertAstronomyPicture(@NotNull APODEntity apod) {
        mRepository.insertAstronomyPicture(apod);
    }
}

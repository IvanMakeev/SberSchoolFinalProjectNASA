package com.example.domain.repository;

import com.example.domain.model.APODEntity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;

/**
 * Репозиторий для получения и сохранения данных
 */

public interface IAstronomyPictureRepository {

    /**
     * Получение данных
     *
     * @param date дата для которой необходимо получить данные
     * @return возвращает Single с данными для отображения пользователю
     */
    @NotNull
    Single<APODEntity> getAstronomyPicture(@NotNull String date);

    /**
     * Сохранение данных
     *
     * @param apod pojo объект для сохранения данных
     */
    void insertAstronomyPicture(@NotNull APODEntity apod);
}

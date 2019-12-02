package com.example.domain.interactor;

import com.example.domain.model.APODEntity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;

/**
 * Интерактор, отвечающий за операции связанные с получением и сохранением данных
 */
public interface IAstronomyPictureInteractor {

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

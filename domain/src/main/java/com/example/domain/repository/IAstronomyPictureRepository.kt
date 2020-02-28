package com.example.domain.repository

import com.example.domain.model.APODEntity
import io.reactivex.Single

/**
 * Репозиторий для получения и сохранения данных
 */
interface IAstronomyPictureRepository {
    /**
     * Получение данных
     *
     * @param date дата для которой необходимо получить данные
     * @return возвращает Single с данными для отображения пользователю
     */
    fun getAstronomyPicture(date: String): Single<APODEntity>

    /**
     * Сохранение данных
     *
     * @param apod pojo объект для сохранения данных
     */
    fun insertAstronomyPicture(apod: APODEntity)
}
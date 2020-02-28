package com.example.domain.repository

import com.example.domain.model.APODEntity

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
    suspend fun getAstronomyPicture(date: String): APODEntity

    /**
     * Сохранение данных
     *
     * @param apod pojo объект для сохранения данных
     */
    suspend fun insertAstronomyPicture(apod: APODEntity)
}
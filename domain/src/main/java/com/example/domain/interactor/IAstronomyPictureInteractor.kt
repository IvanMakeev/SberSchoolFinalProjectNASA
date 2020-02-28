package com.example.domain.interactor

import com.example.domain.model.APODEntity

/**
 * Интерактор, отвечающий за операции связанные с получением и сохранением данных
 */
interface IAstronomyPictureInteractor {
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
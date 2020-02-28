package com.example.domain.interactor

import com.example.domain.exception.CommonException
import com.example.domain.exception.NetworkAccessException
import com.example.domain.model.APODEntity
import com.example.domain.repository.IAstronomyPictureRepository
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Реализация интерактора, отвечающего за операции связанные с получением и сохранением данных
 */
class AstronomyPictureInteractor(private val repository: IAstronomyPictureRepository) : IAstronomyPictureInteractor {

    companion object {
        /**
         * Список содержащий в себе классы с сетевыми исключениями
         */
        private val networkErrorList = listOf(
                UnknownHostException::class.java,
                SocketTimeoutException::class.java,
                ConnectException::class.java
        )
    }

    /**
     * Получение данных
     *
     * @param date дата для которой необходимо получить данные
     * @return возвращает Single с данными для отображения пользователю
     */
    override suspend fun getAstronomyPicture(date: String): APODEntity {
        println("AstronomyPictureInteractor.getAstronomyPicture - ${Thread.currentThread().name}")

        try {
            return repository.getAstronomyPicture(date)
        } catch (throwable: Throwable) {
            val isError = networkErrorList.contains(throwable::class.java)
            val exception = if (isError) NetworkAccessException(throwable) else CommonException(throwable)
            throw exception
        }
    }

    /**
     * Сохранение данных
     *
     * @param apod pojo объект для сохранения данных
     */
    override suspend fun insertAstronomyPicture(apod: APODEntity) {
        println("AstronomyPictureInteractor.insertAstronomyPicture - ${Thread.currentThread().name}")

        repository.insertAstronomyPicture(apod)
    }
}
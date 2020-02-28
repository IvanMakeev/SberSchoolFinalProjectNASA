package com.example.domain.interactor

import com.example.domain.exception.CommonException
import com.example.domain.exception.NetworkAccessException
import com.example.domain.model.APODEntity
import com.example.domain.repository.IAstronomyPictureRepository
import io.reactivex.Single
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Реализация интерактора, отвечающего за операции связанные с получением и сохранением данных
 */
class AstronomyPictureInteractor(private val mRepository: IAstronomyPictureRepository) : IAstronomyPictureInteractor {

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
    override fun getAstronomyPicture(date: String): Single<APODEntity> {
        return mRepository.getAstronomyPicture(date)
                .onErrorResumeNext { throwable: Throwable ->
                    val isError = networkErrorList.contains(throwable::class.java)
                    val exception = if (isError) NetworkAccessException(throwable) else CommonException(throwable)
                    Single.error(exception)
                }
    }

    /**
     * Сохранение данных
     *
     * @param apod pojo объект для сохранения данных
     */
    override fun insertAstronomyPicture(apod: APODEntity) {
        mRepository.insertAstronomyPicture(apod)
    }
}
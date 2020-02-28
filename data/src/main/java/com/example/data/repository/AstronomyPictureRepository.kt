package com.example.data.repository

import com.example.data.api.NasaApi
import com.example.data.database.NasaDao
import com.example.data.mapper.IMapper
import com.example.data.model.APODJson
import com.example.data.model.APODRoom
import com.example.domain.model.APODEntity
import com.example.domain.repository.IAstronomyPictureRepository
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function

/**
 * Реализация репозитория для получения и сохранения данных
 */
class AstronomyPictureRepository
/**
 * @constructor api        используется для работы web api
 * @constructor dao        используется для работы c room api
 * @constructor jsonMapper используется для маппинга данных из json в entity и наоборот
 * @constructor roomMapper используется для маппинга данных из room в entity и наоборот
 */
(
        private val mApi: NasaApi,
        private val mDao: NasaDao,
        private val mJsonMapper: IMapper<APODEntity, APODJson>,
        private val mRoomMapper: IMapper<APODEntity, APODRoom>
) : IAstronomyPictureRepository {
    /**
     * Получение данных
     *
     * @param date дата для которой необходимо получить данные
     * @return возвращает Single с данными для отображения пользователю
     */
    override fun getAstronomyPicture(date: String): Single<APODEntity> {
        return Single.fromCallable { mRoomMapper.mapToEntity(mDao.getAstronomyPicture(date)) }
                .flatMap(getSource(date))
    }

    private fun getSource(date: String): Function<APODEntity, SingleSource<APODEntity>> {
        return Function { apodEntity: APODEntity ->
            if (isDataExist(apodEntity)) {
                return@Function fetchFromDatabase(apodEntity)
            } else {
                return@Function fetchFromNetwork(date)
            }
        }
    }

    private fun isDataExist(apodEntity: APODEntity): Boolean {
        return apodEntity.date != ""
    }

    private fun fetchFromDatabase(apodEntity: APODEntity): SingleSource<APODEntity> {
        return Single.fromCallable { apodEntity }
    }

    private fun fetchFromNetwork(date: String): SingleSource<APODEntity> {
        return mApi.getAstronomyPicture(date)
                .map { type: APODJson? -> mJsonMapper.mapToEntity(type) }
                .doOnSuccess { apod: APODEntity -> mDao.insertAstronomyPicture(mRoomMapper.mapFromEntity(apod)) }
    }

    /**
     * Сохранение данных
     *
     * @param apod pojo объект для сохранения данных
     */
    override fun insertAstronomyPicture(apod: APODEntity) {
        mDao.insertAstronomyPicture(mRoomMapper.mapFromEntity(apod))
    }

}
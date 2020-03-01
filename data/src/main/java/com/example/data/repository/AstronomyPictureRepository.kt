package com.example.data.repository

import com.example.data.api.NasaApi
import com.example.data.database.NasaDao
import com.example.data.mapper.IMapper
import com.example.data.model.APODJson
import com.example.data.model.APODRoom
import com.example.domain.model.APODEntity
import com.example.domain.repository.IAstronomyPictureRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

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
        private val api: NasaApi,
        private val dao: NasaDao,
        private val jsonMapper: IMapper<APODEntity, APODJson>,
        private val roomMapper: IMapper<APODEntity, APODRoom>
) : IAstronomyPictureRepository {
    /**
     * Получение данных
     *
     * @param date дата для которой необходимо получить данные
     * @return возвращает Single с данными для отображения пользователю
     */
    override suspend fun getAstronomyPicture(date: String): APODEntity {
        return when (val entityRoom = fetchFromDatabase(date = date)) {
            null -> {
                val entity = jsonMapper.mapToEntity(fetchFromNetwork(date))
                insertAstronomyPicture(entity)
                entity
            }
            else -> {
                roomMapper.mapToEntity(entityRoom)
            }
        }
    }

    private suspend fun fetchFromDatabase(date: String): APODRoom? = dao.getAstronomyPicture(date)


    private suspend fun fetchFromNetwork(date: String): APODJson = api.getAstronomyPicture(date)


    /**
     * Сохранение данных
     *
     * @param apod pojo объект для сохранения данных
     */
    override suspend fun insertAstronomyPicture(apod: APODEntity) {
        coroutineScope {
            launch {
                dao.insertAstronomyPicture(roomMapper.mapFromEntity(apod))
            }
        }
    }
}
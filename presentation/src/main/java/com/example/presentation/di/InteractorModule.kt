package com.example.presentation.di

import com.example.data.api.NasaApi
import com.example.data.database.NasaDao
import com.example.data.mapper.IMapper
import com.example.data.mapper.JsonMapper
import com.example.data.mapper.RoomMapper
import com.example.data.model.APODJson
import com.example.data.model.APODRoom
import com.example.data.repository.AstronomyPictureRepository
import com.example.domain.interactor.AstronomyPictureInteractor
import com.example.domain.interactor.IAstronomyPictureInteractor
import com.example.domain.model.APODEntity
import com.example.domain.repository.IAstronomyPictureRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Модуль внедренения зависимостей для работы с интерактором
 */
@Module
internal class InteractorModule {
    @Provides
    @Singleton
    @Named(IMapper.JSON)
    fun provideJsonMapper(mapper: JsonMapper): IMapper<APODEntity, APODJson> =
            mapper


    @Provides
    @Singleton
    @Named(IMapper.ROOM)
    fun provideRoomMapper(mapper: RoomMapper): IMapper<APODEntity, APODRoom> =
            mapper


    @Provides
    @Singleton
    fun provideAstronomyPictureRepository(
            dao: NasaDao,
            api: NasaApi,
            @Named(IMapper.JSON) jsonMapper: IMapper<APODEntity, APODJson>,
            @Named(IMapper.ROOM) roomMapper: IMapper<APODEntity, APODRoom>): IAstronomyPictureRepository =
            AstronomyPictureRepository(api, dao, jsonMapper, roomMapper)


    @Provides
    @Singleton
    fun provideAstronomyPictureInteractor(repository: IAstronomyPictureRepository): IAstronomyPictureInteractor =
            AstronomyPictureInteractor(repository)
}
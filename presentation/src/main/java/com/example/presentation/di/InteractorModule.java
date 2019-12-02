package com.example.presentation.di;


import com.example.data.api.NasaApi;
import com.example.data.database.NasaDao;
import com.example.data.mapper.IMapper;
import com.example.data.mapper.JsonMapper;
import com.example.data.mapper.RoomMapper;
import com.example.data.model.APODJson;
import com.example.data.model.APODRoom;
import com.example.data.repository.AstronomyPictureRepository;
import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;
import com.example.domain.interactor.AstronomyPictureInteractor;
import com.example.domain.interactor.IAstronomyPictureInteractor;
import com.example.presentation.utils.scheduler.IBaseSchedulerProvider;
import com.example.presentation.utils.scheduler.SchedulerProvider;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль внедренения зависимостей для работы с интерактором
 */
@Module
class InteractorModule {

    @Provides
    @Singleton
    @Named(IMapper.JSON)
    IMapper<APODEntity, APODJson> provideJsonMapper(JsonMapper mapper) {
        return mapper;
    }

    @Provides
    @Singleton
    @Named(IMapper.ROOM)
    IMapper<APODEntity, APODRoom> provideRoomMapper(RoomMapper mapper) {
        return mapper;
    }


    @Provides
    @Singleton
    IAstronomyPictureRepository provideAstronomyPictureRepository(
            NasaDao dao,
            NasaApi api,
            @Named(IMapper.JSON)
                    IMapper<APODEntity, APODJson> jsonMapper,
            @Named(IMapper.ROOM)
                    IMapper<APODEntity, APODRoom> roomMapper) {
        return new AstronomyPictureRepository(api, dao, jsonMapper, roomMapper);
    }

    @Provides
    @Singleton
    IAstronomyPictureInteractor provideAstronomyPictureService(IAstronomyPictureRepository repository) {
        return new AstronomyPictureInteractor(repository);
    }

    @Provides
    @Singleton
    IBaseSchedulerProvider provideSchedulerProvider(SchedulerProvider provider) {
        return provider;
    }
}

package com.example.presentation.di;

import com.example.data.api.NasaApi;
import com.example.data.database.NasaDao;
import com.example.data.mapper.IMapper;
import com.example.data.model.APODJson;
import com.example.data.model.APODRoom;
import com.example.data.repository.AstronomyPictureRepository;
import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class RepositoryModule {

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
}

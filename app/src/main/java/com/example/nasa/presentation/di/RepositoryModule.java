package com.example.nasa.presentation.di;

import com.example.nasa.data.api.INasaApi;
import com.example.nasa.data.database.NasaDao;
import com.example.nasa.data.mapper.IMapper;
import com.example.nasa.data.model.APODJson;
import com.example.nasa.data.model.APODRoom;
import com.example.nasa.data.repository.AstronomyPictureDBRepository;
import com.example.nasa.data.repository.AstronomyPictureServerRepository;
import com.example.nasa.domain.model.APODEntity;
import com.example.nasa.domain.repository.IAstronomyPictureRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(IAstronomyPictureRepository.SERVER)
    IAstronomyPictureRepository provideAstronomyPictureServerRepository
            (INasaApi mApi,
             @Named(IMapper.JSON)
                     IMapper<APODEntity, APODJson> mJsonMapper) {
        return new AstronomyPictureServerRepository(mApi, mJsonMapper);
    }

    @Provides
    @Singleton
    @Named(IAstronomyPictureRepository.DB)
    IAstronomyPictureRepository provideAstronomyPictureDBRepository
            (NasaDao mDao,
             @Named(IMapper.ROOM)
                     IMapper<APODEntity, APODRoom> mRoomMapper) {
        return new AstronomyPictureDBRepository(mDao, mRoomMapper);
    }
}

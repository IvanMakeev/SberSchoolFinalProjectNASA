package com.example.presentation.di;

import com.example.data.api.INasaApi;
import com.example.data.database.NasaDao;
import com.example.data.mapper.IMapper;
import com.example.data.model.APODJson;
import com.example.data.model.APODRoom;
import com.example.data.repository.AstronomyPictureDBRepository;
import com.example.data.repository.AstronomyPictureNetworkRepository;
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
    @Named(IAstronomyPictureRepository.NETWORK)
    IAstronomyPictureRepository provideAstronomyPictureServerRepository
            (INasaApi mApi,
             @Named(IMapper.JSON)
                     IMapper<APODEntity, APODJson> mJsonMapper) {
        return new AstronomyPictureNetworkRepository(mApi, mJsonMapper);
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

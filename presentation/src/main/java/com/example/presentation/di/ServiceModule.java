package com.example.presentation.di;


import com.example.domain.repository.IAstronomyPictureRepository;
import com.example.domain.service.AstronomyPictureService;
import com.example.domain.service.IAstronomyPictureService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class ServiceModule {

    @Provides
    @Singleton
    IAstronomyPictureService provideAstronomyPictureService(
            @Named(IAstronomyPictureRepository.DB)
                    IAstronomyPictureRepository dbRepository,
            @Named(IAstronomyPictureRepository.NETWORK)
                    IAstronomyPictureRepository serverRepository
    ) {
        return new AstronomyPictureService(serverRepository, dbRepository);
    }
}

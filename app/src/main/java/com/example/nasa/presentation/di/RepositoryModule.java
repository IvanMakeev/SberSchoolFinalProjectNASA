package com.example.nasa.presentation.di;

import com.example.nasa.data.repository.AstronomyPictureDBRepository;
import com.example.nasa.data.repository.AstronomyPictureServerRepository;
import com.example.nasa.domain.repository.IAstronomyPictureRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    @Named(IAstronomyPictureRepository.SERVER)
    public IAstronomyPictureRepository provideAstronomyPictureServerRepository(AstronomyPictureServerRepository repository){
        return repository;
    }

    @Provides
    @Singleton
    @Named(IAstronomyPictureRepository.DB)
    public IAstronomyPictureRepository provideAstronomyPictureDBRepository(AstronomyPictureDBRepository repository){
        return repository;
    }
}

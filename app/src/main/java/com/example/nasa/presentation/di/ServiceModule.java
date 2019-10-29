package com.example.nasa.presentation.di;

import com.example.nasa.data.repository.AstronomyPictureServerRepository;
import com.example.nasa.domain.repository.IAstronomyPictureRepository;
import com.example.nasa.domain.service.AstronomyPictureService;
import com.example.nasa.domain.service.IAstronomyPictureService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    public IAstronomyPictureService provideAstronomyPictureService(AstronomyPictureService service) {
        return service;
    }
}

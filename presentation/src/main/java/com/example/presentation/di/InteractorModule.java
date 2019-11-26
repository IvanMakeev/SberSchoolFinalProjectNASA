package com.example.presentation.di;


import com.example.domain.repository.IAstronomyPictureRepository;
import com.example.domain.interactor.AstronomyPictureInteractor;
import com.example.domain.interactor.IAstronomyPictureInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class InteractorModule {

    @Provides
    @Singleton
    IAstronomyPictureInteractor provideAstronomyPictureService(IAstronomyPictureRepository repository) {
        return new AstronomyPictureInteractor(repository);
    }
}

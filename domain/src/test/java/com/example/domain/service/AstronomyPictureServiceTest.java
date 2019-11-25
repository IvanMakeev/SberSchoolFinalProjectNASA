package com.example.domain.service;

import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AstronomyPictureServiceTest {

    private static final String TEST_DATE = "2019-10-10";

    private APODEntity mEnteredEntity;
    private AstronomyPictureService mService;
    private IAstronomyPictureRepository mDbRepository;

    @Before
    public void setUp() {
        mEnteredEntity = new APODEntity(
                "2019-10-10",
                "some explamation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );

        IAstronomyPictureRepository networkRepository = mock(IAstronomyPictureRepository.class);
        when(networkRepository.getAstronomyPicture(TEST_DATE)).thenReturn(Single.fromCallable(() ->
                mEnteredEntity));

        mDbRepository = mock(IAstronomyPictureRepository.class);
        when(mDbRepository.getAstronomyPicture(TEST_DATE)).thenReturn(Single.fromCallable(() ->
                mEnteredEntity));


        mService = new AstronomyPictureService(networkRepository, mDbRepository);
    }

    @Test
    public void testGetAstronomyPicture() {
        TestObserver<APODEntity> observer = mService.getAstronomyPicture(TEST_DATE).test();
        observer.assertValue(mEnteredEntity);
    }

    @Test
    public void testInsertAstronomyPicture() {

    }

}
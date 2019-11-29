package com.example.domain.interactor;

import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class AstronomyPictureInteractorTest {

    private static final String TEST_DATE = "2019-10-10";

    private APODEntity mEnteredEntity;
    private AstronomyPictureInteractor mInteractor;
    private IAstronomyPictureRepository mRepository;

    @Before
    public void setUp() {
        mEnteredEntity = new APODEntity(
                "2019-10-10",
                "some explamation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );

        mRepository = mock(IAstronomyPictureRepository.class);

        mInteractor = new AstronomyPictureInteractor(mRepository);
    }

    @Test
    public void testGetAstronomyPicture() {
        when(mRepository.getAstronomyPicture(TEST_DATE)).thenReturn(Single.fromCallable(() -> mEnteredEntity));

        TestObserver<APODEntity> observer = mInteractor.getAstronomyPicture(TEST_DATE).test();
        observer.assertValue(mEnteredEntity);
        observer.dispose();
        verify(mRepository).getAstronomyPicture(TEST_DATE);
        verifyZeroInteractions(mRepository);

    }

    @Test
    public void testInsertAstronomyPicture() {
        mInteractor.insertAstronomyPicture(mEnteredEntity);
        verify(mRepository).insertAstronomyPicture(mEnteredEntity);
        verifyZeroInteractions(mRepository);
    }

}
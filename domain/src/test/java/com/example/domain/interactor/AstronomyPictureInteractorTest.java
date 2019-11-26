package com.example.domain.interactor;

import com.example.domain.model.APODEntity;
import com.example.domain.repository.IAstronomyPictureRepository;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AstronomyPictureInteractorTest {

    private static final String TEST_DATE = "2019-10-10";

    private APODEntity mEnteredEntity;
    private AstronomyPictureInteractor mInteractor;

    @Before
    public void setUp() {
        mEnteredEntity = new APODEntity(
                "2019-10-10",
                "some explamation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );

        IAstronomyPictureRepository repository = mock(IAstronomyPictureRepository.class);
        when(repository.getAstronomyPicture(TEST_DATE)).thenReturn(Single.fromCallable(() ->
                mEnteredEntity));

        mInteractor = new AstronomyPictureInteractor(repository);
    }

    @Test
    public void testGetAstronomyPicture() {
        TestObserver<APODEntity> observer = mInteractor.getAstronomyPicture(TEST_DATE).test();
        observer.assertValue(mEnteredEntity);
    }

    @Test
    public void testInsertAstronomyPicture() {
    }

}
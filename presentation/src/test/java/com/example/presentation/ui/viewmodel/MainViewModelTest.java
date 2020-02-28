package com.example.presentation.ui.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.domain.exception.CommonException;
import com.example.domain.exception.NetworkAccessException;
import com.example.domain.interactor.IAstronomyPictureInteractor;
import com.example.domain.model.APODEntity;
import com.example.presentation.utils.DateUtils;
import com.example.presentation.utils.scheduler.IBaseSchedulerProvider;
import com.example.presentation.utils.scheduler.TrampolineSchedulerProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.reactivex.Single;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    private APODEntity mEnteredEntityPicture;
    private APODEntity mEnteredEntityVideo;
    private final int mCurrentPositionPageAdapter = 0;
    private final String testDate = DateUtils.getDateOffset(mCurrentPositionPageAdapter);


    private IAstronomyPictureInteractor mInteractor;
    private MainViewModel mViewModel;
    private IBaseSchedulerProvider mSchedulerProvider;

    @Before
    public void setUp() {

        mEnteredEntityPicture = new APODEntity(
                testDate,
                "some explanation",
                "title",
                "https://apod.nasa.gov/apod/image/picture.png",
                "copyright"
        );

        mEnteredEntityVideo = new APODEntity(
                testDate,
                "some explanation",
                "title",
                "https://www.youtube.com/embed/ofZTOxC9JQ4?rel=0",
                "copyright"
        );

        mInteractor = mock(IAstronomyPictureInteractor.class);
        mSchedulerProvider = new TrampolineSchedulerProvider();
        mViewModel = new MainViewModel(mInteractor, mSchedulerProvider, mCurrentPositionPageAdapter);

    }

    @Test
    public void testShowInformationPicture() {
        when(mInteractor.getAstronomyPicture(any())).thenReturn(Single.fromCallable(() -> mEnteredEntityPicture));

        mViewModel.showInformation();

        verify(mInteractor).getAstronomyPicture(testDate);
        verifyZeroInteractions(mInteractor);
        assertEquals(true, mViewModel.isLoadingPicture().getValue());
        assertEquals(false, mViewModel.isLoadingData().getValue());
        assertFalse(mViewModel.isErrorVisible().get());
        assertEquals(false, mViewModel.isNetworkError().getValue());
        assertEquals(true, mViewModel.isPictureView().getValue());
        assertEquals(mEnteredEntityPicture.getTitle(), mViewModel.getTitle().get());
        assertEquals(mEnteredEntityPicture.getExplanation(), mViewModel.getExplanation().get());
        assertEquals(mEnteredEntityPicture.getUrl(), mViewModel.getUrlPicture().get());
        assertEquals(mEnteredEntityPicture.getCopyright(), mViewModel.getCopyright().get());
    }

    @Test
    public void testShowInformationVideo() {
        when(mInteractor.getAstronomyPicture(any())).thenReturn(Single.fromCallable(() -> mEnteredEntityVideo));

        mViewModel.showInformation();

        verify(mInteractor).getAstronomyPicture(testDate);
        verifyZeroInteractions(mInteractor);
        assertEquals(true, mViewModel.isLoadingPicture().getValue());
        assertEquals(false, mViewModel.isLoadingData().getValue());
        assertFalse(mViewModel.isErrorVisible().get());
        assertEquals(false, mViewModel.isNetworkError().getValue());
        assertEquals(false, mViewModel.isPictureView().getValue());
        assertEquals(mEnteredEntityVideo.getTitle(), mViewModel.getTitle().get());
        assertEquals(mEnteredEntityVideo.getExplanation(), mViewModel.getExplanation().get());
        assertEquals(mEnteredEntityVideo.getCopyright(), mViewModel.getCopyright().get());
    }

    @Test
    public void testShowInformationNetworkAccessException() {
        when(mInteractor.getAstronomyPicture(testDate)).thenReturn(Single.error(new NetworkAccessException(new Throwable())));

        mViewModel.showInformation();

        verify(mInteractor).getAstronomyPicture(testDate);
        verifyZeroInteractions(mInteractor);
        assertEquals(true, mViewModel.isLoadingPicture().getValue());
        assertEquals(false, mViewModel.isLoadingData().getValue());
        assertTrue(mViewModel.isErrorVisible().get());
        assertEquals(true, mViewModel.isNetworkError().getValue());
        assertNull(mViewModel.getTitle().get());
        assertNull(mViewModel.getExplanation().get());
        assertNull(mViewModel.getUrlPicture().get());
        assertNull(mViewModel.getCopyright().get());

    }

    @Test
    public void testShowInformationCommonException() {
        when(mInteractor.getAstronomyPicture(testDate)).thenReturn(Single.error(new CommonException(new Throwable())));

        mViewModel.showInformation();

        verify(mInteractor).getAstronomyPicture(testDate);
        verifyZeroInteractions(mInteractor);
        assertEquals(true, mViewModel.isLoadingPicture().getValue());
        assertEquals(false, mViewModel.isLoadingData().getValue());
        assertTrue(mViewModel.isErrorVisible().get());
        assertEquals(false, mViewModel.isNetworkError().getValue());
        assertNull(mViewModel.getTitle().get());
        assertNull(mViewModel.getExplanation().get());
        assertNull(mViewModel.getUrlPicture().get());
        assertNull(mViewModel.getCopyright().get());
    }


}
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

    private APODEntity mEnteredEntity;
    private int mCurrentPositionPageAdapter = 0;
    private String testDate = DateUtils.getDateOffset(mCurrentPositionPageAdapter);


    private IAstronomyPictureInteractor mInteractor;
    private MainViewModel mViewModel;
    private IBaseSchedulerProvider mSchedulerProvider;

    @Before
    public void setUp()  {

        mEnteredEntity = new APODEntity(
                testDate,
                "some explamation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        );

        mInteractor = mock(IAstronomyPictureInteractor.class);
        mSchedulerProvider = new TrampolineSchedulerProvider();
        mViewModel = new MainViewModel(mInteractor, mSchedulerProvider, mCurrentPositionPageAdapter);

    }

    @Test
    public void testShowInformation() {
        when(mInteractor.getAstronomyPicture(any())).thenReturn(Single.fromCallable(() -> mEnteredEntity));

        mViewModel.showInformation();

        verify(mInteractor).getAstronomyPicture(testDate);
        verifyZeroInteractions(mInteractor);
        assertEquals(true, mViewModel.getIsLoadingPicture().getValue());
        assertEquals(false, mViewModel.getIsLoadingData().getValue());
        assertFalse(mViewModel.getIsErrorVisible().get());
        assertEquals(false, mViewModel.getIsNetworkError().getValue());
        assertEquals(mEnteredEntity.getTitle(), mViewModel.getTitle().get());
        assertEquals(mEnteredEntity.getExplanation(), mViewModel.getExplanation().get());
        assertEquals(mEnteredEntity.getUrl(), mViewModel.getUrlPicture().get());
        assertEquals(mEnteredEntity.getCopyright(), mViewModel.getCopyright().get());
    }

    @Test
    public void testShowInformationNetworkAccessException() {
        when(mInteractor.getAstronomyPicture(any())).thenReturn(Single.error(new NetworkAccessException(new Throwable())));

        mViewModel.showInformation();

        verify(mInteractor).getAstronomyPicture(testDate);
        verifyZeroInteractions(mInteractor);
        assertEquals(true, mViewModel.getIsLoadingPicture().getValue());
        assertEquals(false, mViewModel.getIsLoadingData().getValue());
        assertTrue(mViewModel.getIsErrorVisible().get());
        assertEquals(true, mViewModel.getIsNetworkError().getValue());
        assertNull(mViewModel.getTitle().get());
        assertNull(mViewModel.getExplanation().get());
        assertNull(mViewModel.getUrlPicture().get());
        assertNull(mViewModel.getCopyright().get());

    }

    @Test
    public void testShowInformationCommonException() {
        when(mInteractor.getAstronomyPicture(any())).thenReturn(Single.error(new CommonException(new Throwable())));

        mViewModel.showInformation();

        verify(mInteractor).getAstronomyPicture(testDate);
        verifyZeroInteractions(mInteractor);
        assertEquals(true, mViewModel.getIsLoadingPicture().getValue());
        assertEquals(false, mViewModel.getIsLoadingData().getValue());
        assertTrue(mViewModel.getIsErrorVisible().get());
        assertEquals(false, mViewModel.getIsNetworkError().getValue());
        assertNull(mViewModel.getTitle().get());
        assertNull(mViewModel.getExplanation().get());
        assertNull(mViewModel.getUrlPicture().get());
        assertNull(mViewModel.getCopyright().get());
    }


}
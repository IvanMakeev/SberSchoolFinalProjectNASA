package com.example.presentation.ui.viewmodel;

import com.example.domain.interactor.AstronomyPictureInteractor;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.android.plugins.RxAndroidPlugins;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainViewModelTest {

//    @Rule
//    InstantTaskExecutorRule
    
    private AstronomyPictureInteractor mInteractor;
    private MainViewModel mViewModel;

    @Before
    public void setUp() throws Exception {
        mInteractor = mock(AstronomyPictureInteractor.class);
        mViewModel = new MainViewModel(mInteractor);
    }

    @Test
    public void testShowInformation() {
        RxAndroidPlugins.getOnMainThreadSchedulerHandler();
        mViewModel.showInformation(1);
        verify(mInteractor).getAstronomyPicture(any());
    }
}
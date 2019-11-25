package com.example.presentation.ui.viewmodel;

import com.example.domain.service.AstronomyPictureService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.reactivex.android.plugins.RxAndroidPlugins;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainViewModelTest {

//    @Rule
//    InstantTaskExecutorRule
    
    private AstronomyPictureService mService;
    private MainViewModel mViewModel;

    @Before
    public void setUp() throws Exception {
        mService = mock(AstronomyPictureService.class);
        mViewModel = new MainViewModel(mService);
    }

    @Test
    public void testShowInformation() {
        RxAndroidPlugins.getOnMainThreadSchedulerHandler();
        mViewModel.showInformation(1);
        verify(mService).getAstronomyPicture(any());
    }
}
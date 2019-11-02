package com.example.nasa.presentation.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.nasa.domain.service.IAstronomyPictureService;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private IAstronomyPictureService mService;
    private int mCurrentPosition;


    public MainViewModelFactory(IAstronomyPictureService service, int currentPosition) {
        mService = service;
        mCurrentPosition = currentPosition;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(mService, mCurrentPosition);
    }
}

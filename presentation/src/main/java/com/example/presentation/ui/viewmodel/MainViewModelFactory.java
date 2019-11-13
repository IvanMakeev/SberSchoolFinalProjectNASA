package com.example.presentation.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.domain.service.IAstronomyPictureService;

import org.jetbrains.annotations.NotNull;


public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final IAstronomyPictureService mService;

    public MainViewModelFactory(@NotNull IAstronomyPictureService service) {
        mService = service;
    }

    @SuppressWarnings("unchecked cast")
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        return (T) new MainViewModel(mService);
    }
}

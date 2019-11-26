package com.example.presentation.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.domain.interactor.IAstronomyPictureInteractor;

import org.jetbrains.annotations.NotNull;


public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NotNull
    private final IAstronomyPictureInteractor mInteractor;

    public MainViewModelFactory(@NotNull IAstronomyPictureInteractor interactor) {
        mInteractor = interactor;
    }

    @SuppressWarnings("unchecked cast")
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        return (T) new MainViewModel(mInteractor);
    }
}

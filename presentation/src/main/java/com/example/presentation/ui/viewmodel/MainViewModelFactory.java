package com.example.presentation.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.domain.interactor.IAstronomyPictureInteractor;
import com.example.presentation.utils.scheduler.IBaseSchedulerProvider;

import org.jetbrains.annotations.NotNull;


public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NotNull
    private final IAstronomyPictureInteractor mInteractor;
    @NotNull
    private final IBaseSchedulerProvider mSchedulerProvider;
    private final int mCurrentPositionPageAdapter;

    public MainViewModelFactory(@NotNull IAstronomyPictureInteractor interactor,
                                @NotNull IBaseSchedulerProvider schedulerProvider,
                                int currentPositionPageAdapter) {
        mInteractor = interactor;
        mSchedulerProvider = schedulerProvider;
        mCurrentPositionPageAdapter = currentPositionPageAdapter;
    }

    @SuppressWarnings("unchecked cast")
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        return (T) new MainViewModel(mInteractor, mSchedulerProvider, mCurrentPositionPageAdapter);
    }
}

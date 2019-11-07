package com.example.presentation.ui.viewmodel;

import android.annotation.SuppressLint;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.domain.model.APODEntity;
import com.example.domain.service.IAstronomyPictureService;
import com.example.presentation.utils.DateUtils;
import com.example.presentation.utils.ErrorUtils;

import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainViewModel extends ViewModel {

    private final ObservableField<String> mTitle = new ObservableField<>();
    private final ObservableField<String> mExplanation = new ObservableField<>();
    private final ObservableField<String> mUrlPicture = new ObservableField<>();
    private final ObservableField<String> mCopyright = new ObservableField<>();
    private final ObservableBoolean isErrorVisible = new ObservableBoolean(false);
    private final MutableLiveData<Boolean> isNetworkError = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    private final int mCurrentPosition;
    private final IAstronomyPictureService mService;

    public MainViewModel(IAstronomyPictureService service, int currentPosition) {
        mService = service;
        mCurrentPosition = currentPosition;
    }

    @SuppressLint("CheckResult")
    public void showInformation() {
        mService.getAstronomyPicture(DateUtils.getDateOffset(mCurrentPosition))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    isLoading.setValue(true);
                    isErrorVisible.set(false);
                })
                .subscribe(this::bindView,
                        throwable -> {
                            if (ErrorUtils.checkNetworkError(throwable)) {
                                isNetworkError.setValue(true);
                            } else {
                                isNetworkError.setValue(false);
                            }
                            isErrorVisible.set(true);
                        });
    }

    private void bindView(@NotNull APODEntity apodEntity) {
        mTitle.set(apodEntity.getTitle());
        mExplanation.set(apodEntity.getExplanation());
        mUrlPicture.set(apodEntity.getUrl());
        mCopyright.set(apodEntity.getCopyright());
    }

    @NotNull
    public ObservableField<String> getTitle() {
        return mTitle;
    }

    @NotNull
    public ObservableField<String> getExplanation() {
        return mExplanation;
    }

    @NotNull
    public ObservableField<String> getUrlPicture() {
        return mUrlPicture;
    }

    @NotNull
    public ObservableField<String> getCopyright() {
        return mCopyright;
    }

    @NotNull
    public ObservableBoolean getIsErrorVisible() {
        return isErrorVisible;
    }

    public LiveData<Boolean> getIsNetworkError() {
        return isNetworkError;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}

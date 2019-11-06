package com.example.nasa.presentation.ui.viewmodel;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nasa.domain.model.APODEntity;
import com.example.nasa.domain.service.IAstronomyPictureService;
import com.example.nasa.presentation.utils.DateUtils;
import com.example.nasa.presentation.utils.ErrorUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainViewModel extends ViewModel {

    private ObservableField<String> mTitle = new ObservableField<>();
    private ObservableField<String> mExplanation = new ObservableField<>();
    private ObservableField<String> mUrlPicture = new ObservableField<>();
    private ObservableField<String> mCopyright = new ObservableField<>();
    private ObservableBoolean isErrorVisible = new ObservableBoolean(false);
    private MutableLiveData<Boolean> isNetworkError = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);


    private int mCurrentPosition;
    private IAstronomyPictureService mService;

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

    private void bindView(@NonNull APODEntity apodEntity) {
        mTitle.set(apodEntity.getTitle());
        mExplanation.set(apodEntity.getExplanation());
        mUrlPicture.set(apodEntity.getUrl());
        mCopyright.set(apodEntity.getCopyright());
    }

    @NonNull
    public ObservableField<String> getTitle() {
        return mTitle;
    }

    @NonNull
    public ObservableField<String> getExplanation() {
        return mExplanation;
    }

    @NonNull
    public ObservableField<String> getUrlPicture() {
        return mUrlPicture;
    }

    @NonNull
    public ObservableField<String> getCopyright() {
        return mCopyright;
    }

    @NonNull
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

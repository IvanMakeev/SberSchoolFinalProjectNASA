package com.example.nasa.presentation.ui.viewmodel;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.nasa.domain.model.APODEntity;
import com.example.nasa.domain.service.IAstronomyPictureService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainViewModel extends ViewModel {


    private ObservableField<String> mTitle = new ObservableField<>();
    private ObservableField<String> mExplanation = new ObservableField<>();
    private ObservableField<String> mUrlPicture = new ObservableField<>();
    private ObservableField<String> mCopyright = new ObservableField<>();
    private ObservableBoolean isErrorVisible = new ObservableBoolean(false);


    private int mCurrentPosition;

    private IAstronomyPictureService mService;

    public MainViewModel(IAstronomyPictureService service, int currentPosition) {
        mService = service;
        mCurrentPosition = currentPosition;
    }

    @SuppressLint("CheckResult")
    public void showInformation() {
        mService.getAstronomyPicture(getDateOffset())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> isErrorVisible.set(false))
                .subscribe(this::bindView,
                        throwable -> {
                            isErrorVisible.set(true);
                            throwable.printStackTrace();
                        });
    }


    @NonNull
    private String getDateOffset() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -mCurrentPosition);
        Date dateWithOffset = calendar.getTime();
        return formattingDate(dateWithOffset);
    }

    @NonNull
    private String formattingDate(@NonNull Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(date);
    }


    private void bindView(@NonNull APODEntity apodEntity) {
        mTitle.set(apodEntity.getTitle());
        mExplanation.set(apodEntity.getExplanation());
        mUrlPicture.set(apodEntity.getUrl());
        mCopyright.set(apodEntity.getCopyright());
    }

    public ObservableField<String> getTitle() {
        return mTitle;
    }

    public ObservableField<String> getExplanation() {
        return mExplanation;
    }

    public ObservableField<String> getUrlPicture() {
        return mUrlPicture;
    }

    public ObservableField<String> getCopyright() {
        return mCopyright;
    }

    public ObservableBoolean getIsErrorVisible() {
        return isErrorVisible;
    }
}

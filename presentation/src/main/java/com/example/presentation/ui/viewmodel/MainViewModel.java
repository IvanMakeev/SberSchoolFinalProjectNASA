package com.example.presentation.ui.viewmodel;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.domain.exception.NetworkAccessException;
import com.example.domain.model.APODEntity;
import com.example.domain.interactor.IAstronomyPictureInteractor;
import com.example.presentation.utils.DateUtils;
import com.example.presentation.utils.scheduler.IBaseSchedulerProvider;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.CompositeDisposable;

/**
 * ViewModel предоставления астронамической картинки
 */
public class MainViewModel extends ViewModel {

    private final ObservableField<String> mTitle = new ObservableField<>();
    private final ObservableField<String> mExplanation = new ObservableField<>();
    private final ObservableField<String> mUrlPicture = new ObservableField<>();
    private final ObservableField<String> mCopyright = new ObservableField<>();
    private final ObservableBoolean isErrorVisible = new ObservableBoolean(false);
    private final MutableLiveData<Boolean> isNetworkError = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isLoadingPicture = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isLoadingData = new MutableLiveData<>(false);

    @NotNull
    private final IAstronomyPictureInteractor mInteractor;
    @NotNull
    private final IBaseSchedulerProvider mSchedulerProvider;
    private final int mCurrentPositionViewPage;
    @NotNull
    private final CompositeDisposable mCompositeDisposable;

    /**
     * @param interactor                 экземпляр интерактора
     * @param schedulerProvider          обертка над планировщиками RxJava
     * @param currentPositionPageAdapter текущая позиция PageAdapter'a на экране
     */
    public MainViewModel(@NotNull IAstronomyPictureInteractor interactor,
                         @NotNull IBaseSchedulerProvider schedulerProvider,
                         int currentPositionPageAdapter) {
        mInteractor = interactor;
        mSchedulerProvider = schedulerProvider;
        mCurrentPositionViewPage = currentPositionPageAdapter;
        mCompositeDisposable = new CompositeDisposable();
    }

    /**
     * Загрузка и отображение данных на экране
     */
    public void showInformation() {
        mCompositeDisposable.add(
                mInteractor.getAstronomyPicture(DateUtils.getDateOffset(mCurrentPositionViewPage))
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.mainThread())
                        .doOnSubscribe(disposable -> {
                            isLoadingData.setValue(true);
                            isLoadingPicture.setValue(true);
                            isErrorVisible.set(false);
                        })
                        .doFinally(() -> isLoadingData.setValue(false))
                        .subscribe(this::bindView,
                                throwable -> {
                                    if (throwable instanceof NetworkAccessException) {
                                        isNetworkError.setValue(true);
                                    }
                                    isErrorVisible.set(true);
                                })
        );
    }

    private void bindView(@NotNull APODEntity apodEntity) {
        mTitle.set(apodEntity.getTitle());
        mExplanation.set(apodEntity.getExplanation());
        mUrlPicture.set(apodEntity.getUrl());
        mCopyright.set(apodEntity.getCopyright());
    }

    /**
     * Обновление данных на экране в случае возникновения ошибки
     *
     * @return возвращает OnRefreshListener
     */
    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return this::showInformation;
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
    }

    /**
     * Строка с заголовком
     */
    @NotNull
    public ObservableField<String> getTitle() {
        return mTitle;
    }

    /**
     * Строка с описанием
     */
    @NotNull
    public ObservableField<String> getExplanation() {
        return mExplanation;
    }

    /**
     * Строка с url картинки
     */
    @NotNull
    public ObservableField<String> getUrlPicture() {
        return mUrlPicture;
    }

    /**
     * Строка с полем copyright
     */
    @NotNull
    public ObservableField<String> getCopyright() {
        return mCopyright;
    }

    /**
     * Состояние видимости ошибки
     */
    @NotNull
    public ObservableBoolean getIsErrorVisible() {
        return isErrorVisible;
    }

    /**
     * Состояние видимости сетевой ошибки
     */
    @NotNull
    public LiveData<Boolean> getIsNetworkError() {
        return isNetworkError;
    }

    /**
     * Состояние загрузки картинки (используется для progress bar)
     */
    @NotNull
    public LiveData<Boolean> getIsLoadingPicture() {
        return isLoadingPicture;
    }

    /**
     * Состояние загрузки данных
     */
    public LiveData<Boolean> getIsLoadingData() {
        return isLoadingData;
    }
}

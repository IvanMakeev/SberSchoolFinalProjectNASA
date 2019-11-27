package com.example.presentation.ui.viewmodel;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.domain.model.APODEntity;
import com.example.domain.interactor.IAstronomyPictureInteractor;
import com.example.presentation.utils.DateUtils;
import com.example.presentation.utils.ErrorUtils;

import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

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
    private int mCurrentPositionViewPage;
    @NotNull
    private final CompositeDisposable mCompositeDisposable;

    public MainViewModel(@NotNull IAstronomyPictureInteractor interactor, int currentPositionPageAdapter) {
        mInteractor = interactor;
        mCurrentPositionViewPage = currentPositionPageAdapter;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void showInformation() {
        mCompositeDisposable.add(mInteractor.getAstronomyPicture(DateUtils.getDateOffset(mCurrentPositionViewPage))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    isLoadingData.setValue(true);
                    isLoadingPicture.setValue(true);
                    isErrorVisible.set(false);
                })
                .doFinally(() -> isLoadingData.postValue(false))
                .subscribe(this::bindView,
                        throwable -> {
                            if (ErrorUtils.checkNetworkError(throwable)) {
                                isNetworkError.setValue(true);
                            } else {
                                isNetworkError.setValue(false);
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

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return this::showInformation;
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
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

    @NotNull
    public LiveData<Boolean> getIsNetworkError() {
        return isNetworkError;
    }

    @NotNull
    public LiveData<Boolean> getIsLoadingPicture() {
        return isLoadingPicture;
    }

    public LiveData<Boolean> getIsLoadingData() {
        return isLoadingData;
    }
}

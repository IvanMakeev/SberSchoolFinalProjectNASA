package com.example.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.domain.interactor.IAstronomyPictureInteractor
import com.example.presentation.utils.scheduler.IBaseSchedulerProvider

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory
/**
 * @constructor interactor                 экземпляр интерактора
 * @constructor schedulerProvider          обертка над планировщиками RxJava
 * @constructor currentPositionPageAdapter текущая позиция PageAdapter'a на экране
 */
(
        private val mInteractor: IAstronomyPictureInteractor,
        private val mSchedulerProvider: IBaseSchedulerProvider,
        private val mCurrentPositionPageAdapter: Int
) : NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            MainViewModel(mInteractor, mSchedulerProvider, mCurrentPositionPageAdapter) as T

}
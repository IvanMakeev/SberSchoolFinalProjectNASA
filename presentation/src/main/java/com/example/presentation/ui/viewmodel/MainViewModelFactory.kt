package com.example.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.domain.interactor.IAstronomyPictureInteractor

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory
/**
 * @constructor interactor                 экземпляр интерактора
 * @constructor schedulerProvider          обертка над планировщиками RxJava
 * @constructor currentPositionPageAdapter текущая позиция PageAdapter'a на экране
 */
(
        private val interactor: IAstronomyPictureInteractor,
        private val currentPositionPageAdapter: Int
) : NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            MainViewModel(interactor, currentPositionPageAdapter) as T

}
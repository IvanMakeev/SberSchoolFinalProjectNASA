package com.example.presentation.utils.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Реализация интерфейса-обертки над планировщиками RxJava
 */
class SchedulerProvider @Inject constructor() : IBaseSchedulerProvider {
    /**
     * Обертка над io планировщиком
     */
    override fun io() = Schedulers.io()


    /**
     * Обертка над computation планировщиком
     */
    override fun computation() = Schedulers.computation()


    /**
     * Обертка над планировщиком главного потока
     */
    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()

}
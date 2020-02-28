package com.example.presentation.utils.scheduler

import io.reactivex.Scheduler

/**
 * Интерфейс-обертка над планировщиками RxJava
 */
interface IBaseSchedulerProvider {
    /**
     * Обертка над io планировщиком
     */
    fun io(): Scheduler

    /**
     * Обертка над computation планировщиком
     */
    fun computation(): Scheduler

    /**
     * Обертка над планировщиком главного потока
     */
    fun mainThread(): Scheduler
}
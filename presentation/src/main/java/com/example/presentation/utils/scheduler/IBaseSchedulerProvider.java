package com.example.presentation.utils.scheduler;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Scheduler;

/**
 * Интерфейс-обертка над планировщиками RxJava
 */
public interface IBaseSchedulerProvider {

    /**
     * Обертка над io планировщиком
     */
    @NotNull
    Scheduler io();

    /**
     * Обертка над computation планировщиком
     */
    @NotNull
    Scheduler computation();

    /**
     * Обертка над планировщиком главного потока
     */
    @NotNull
    Scheduler mainThread();
}

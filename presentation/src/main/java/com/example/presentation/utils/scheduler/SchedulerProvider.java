package com.example.presentation.utils.scheduler;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Реализация интерфейса-обертки над планировщиками RxJava
 */
public class SchedulerProvider implements IBaseSchedulerProvider {

    @Inject
    public SchedulerProvider() {
    }

    /**
     * Обертка над io планировщиком
     */
    @NotNull
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    /**
     * Обертка над computation планировщиком
     */
    @NotNull
    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    /**
     * Обертка над планировщиком главного потока
     */
    @NotNull
    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }
}

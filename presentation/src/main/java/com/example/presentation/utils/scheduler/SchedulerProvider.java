package com.example.presentation.utils.scheduler;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider implements IBaseSchedulerProvider {

    @Inject
    public SchedulerProvider() {
    }

    @NotNull
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @NotNull
    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @NotNull
    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }
}

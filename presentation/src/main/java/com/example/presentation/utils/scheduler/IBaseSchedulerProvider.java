package com.example.presentation.utils.scheduler;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Scheduler;

public interface IBaseSchedulerProvider {

    @NotNull
    Scheduler io();

    @NotNull
    Scheduler computation();

    @NotNull
    Scheduler mainThread();
}

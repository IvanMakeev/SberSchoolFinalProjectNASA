package com.example.presentation.utils.scheduler;

import io.reactivex.Scheduler;

public interface IBaseSchedulerProvider {
    Scheduler io();

    Scheduler computation();

    Scheduler mainThread();
}

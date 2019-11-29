package com.example.presentation.utils.scheduler;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TrampolineSchedulerProvider implements IBaseSchedulerProvider {

    @NotNull
    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @NotNull
    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    @NotNull
    @Override
    public Scheduler mainThread() {
        return Schedulers.trampoline();
    }
}

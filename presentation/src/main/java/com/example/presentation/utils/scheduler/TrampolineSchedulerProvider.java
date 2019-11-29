package com.example.presentation.utils.scheduler;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TrampolineSchedulerProvider implements IBaseSchedulerProvider {
    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler mainThread() {
        return Schedulers.trampoline();
    }
}

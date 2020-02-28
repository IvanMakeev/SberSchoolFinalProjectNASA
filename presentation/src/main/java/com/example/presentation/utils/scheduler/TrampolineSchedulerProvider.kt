package com.example.presentation.utils.scheduler

import io.reactivex.schedulers.Schedulers

/**
 * Реализация интерфейса-обертки над планировщиками RxJava, используется для тестирования
 */
class TrampolineSchedulerProvider : IBaseSchedulerProvider {
    override fun io() = Schedulers.trampoline()


    override fun computation() = Schedulers.trampoline()


    override fun mainThread() = Schedulers.trampoline()
}
package com.example.domain.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception определяющий общую ошибку в работе приложения
 */
public class CommonException extends Exception {
    public CommonException(@NotNull Throwable throwable) {
        super(throwable);
    }
}

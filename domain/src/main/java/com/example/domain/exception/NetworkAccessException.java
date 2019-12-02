package com.example.domain.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception определяющий сетевую ошибку в работе приложения
 */
public class NetworkAccessException extends Exception {
    public NetworkAccessException(@NotNull Throwable throwable) {
        super(throwable);
    }
}

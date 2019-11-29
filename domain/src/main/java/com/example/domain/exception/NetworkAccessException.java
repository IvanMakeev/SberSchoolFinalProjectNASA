package com.example.domain.exception;

import org.jetbrains.annotations.NotNull;

public class NetworkAccessException extends Exception {
    public NetworkAccessException(@NotNull Throwable throwable) {
        super(throwable);
    }
}

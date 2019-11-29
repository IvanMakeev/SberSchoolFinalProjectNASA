package com.example.domain.exception;

import org.jetbrains.annotations.NotNull;

public class CommonException extends Exception {
    public CommonException(@NotNull Throwable throwable) {
        super(throwable);
    }
}

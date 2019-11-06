package com.example.nasa.presentation.utils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.exceptions.CompositeException;

public class ErrorUtils {

    private static List<Class<? extends IOException>> networkErrorList = Arrays.asList(
            UnknownHostException.class,
            SocketTimeoutException.class,
            ConnectException.class
    );

    public static boolean checkNetworkError(Throwable throwable) {
        if (throwable instanceof CompositeException) {
            for (Throwable exception : ((CompositeException) throwable).getExceptions()) {
                if (exception.getCause() != null) {
                    return checkContains(exception.getCause().getClass());
                }
            }
        } else {
            return checkContains(throwable.getClass());
        }
        return false;
    }

    private static boolean checkContains(Class klass) {
        return networkErrorList.contains(klass);
    }
}

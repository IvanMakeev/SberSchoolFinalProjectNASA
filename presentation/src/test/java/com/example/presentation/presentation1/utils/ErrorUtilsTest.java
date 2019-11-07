package com.example.presentation.presentation1.utils;

import com.example.presentation.utils.ErrorUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.exceptions.CompositeException;

import static org.junit.Assert.*;

public class ErrorUtilsTest {

    private List<Class<? extends IOException>> mNetworkErrorList;

    @Before
    public void setUp() throws Exception {
        mNetworkErrorList = Arrays.asList(
                UnknownHostException.class,
                SocketTimeoutException.class,
                ConnectException.class
        );
    }

    @Test
    public void checkNetworkError() throws InstantiationException, IllegalAccessException {
        for (Class<? extends IOException> klass : mNetworkErrorList) {
            assertTrue(ErrorUtils.checkNetworkError(klass.newInstance()));

            assertTrue(ErrorUtils.checkNetworkError(new CompositeException(new RuntimeException(klass.newInstance()))));
        }


    }
}
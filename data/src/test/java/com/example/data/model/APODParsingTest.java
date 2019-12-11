package com.example.data.model;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

public class APODParsingTest {

    private InputStream mInputStream;
    private APODJson mExpectedResult;

    @Before
    public void setUp() {
        mInputStream = getClass().getClassLoader().getResourceAsStream("test_parse.json");

        mExpectedResult = new APODJson(
                "2019-11-15",
                "A star cluster around 2 million years young surrounded by natal clouds of dust and glowing gas.",
                "M16 and the Eagle Nebula",
                "https://apod.nasa.gov/apod/image/1911/M16_HaSynLumLumRGB1024.jpg",
                "Martin Pugh"
        );
    }

    @Test
    public void testParse() {
        APODJson resultAPODJson = new Gson().fromJson(new InputStreamReader(mInputStream), APODJson.class);
        Assert.assertEquals(mExpectedResult, resultAPODJson);
    }
}
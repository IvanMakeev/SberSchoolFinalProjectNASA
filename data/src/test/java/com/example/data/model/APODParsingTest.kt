package com.example.data.model

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.InputStream
import java.io.InputStreamReader

class APODParsingTest {
    private lateinit var inputStream: InputStream
    private lateinit var expectedResult: APODJson
    @Before
    fun setUp() {
        inputStream = javaClass.classLoader!!.getResourceAsStream("test_parse.json")
        expectedResult = APODJson(
                "2019-11-15",
                "A star cluster around 2 million years young surrounded by natal clouds of dust and glowing gas.",
                "M16 and the Eagle Nebula",
                "https://apod.nasa.gov/apod/image/1911/M16_HaSynLumLumRGB1024.jpg",
                "Martin Pugh"
        )
    }

    @Test
    fun testParse() {
        val resultAPODJson = Gson().fromJson(InputStreamReader(inputStream), APODJson::class.java)
        Assert.assertEquals(expectedResult, resultAPODJson)
    }
}
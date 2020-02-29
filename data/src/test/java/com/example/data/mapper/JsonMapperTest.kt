package com.example.data.mapper

import com.example.data.model.APODJson
import com.example.domain.model.APODEntity
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class JsonMapperTest {
    private lateinit var mapper: JsonMapper
    private lateinit var enteredJson: APODJson
    private lateinit var enteredEntity: APODEntity
    @Before
    fun setUp() {
        mapper = JsonMapper()
        enteredJson = APODJson(
                "2019-10-10",
                "some explanation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        )
        enteredEntity = APODEntity(
                "2019-10-10",
                "some explanation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        )
    }

    @Test
    fun testMapFromEntity() {
        val expected = mapper.mapFromEntity(enteredEntity)
        MatcherAssert.assertThat(enteredJson, CoreMatchers.`is`(expected))
    }

    @Test
    fun testMapToEntity() {
        val expected = mapper.mapToEntity(enteredJson)
        MatcherAssert.assertThat(enteredEntity, CoreMatchers.`is`(expected))
    }
}
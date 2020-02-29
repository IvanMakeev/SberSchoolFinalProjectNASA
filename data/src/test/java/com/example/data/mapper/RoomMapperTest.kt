package com.example.data.mapper

import com.example.data.model.APODRoom
import com.example.domain.model.APODEntity
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class RoomMapperTest {
    private lateinit var mapper: RoomMapper
    private lateinit var enteredRoom: APODRoom
    private lateinit var enteredEntity: APODEntity
    @Before
    fun setUp() {
        mapper = RoomMapper()
        enteredRoom = APODRoom(
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
        MatcherAssert.assertThat(enteredRoom, CoreMatchers.`is`(expected))
    }

    @Test
    fun testMapToEntity() {
        val expected = mapper.mapToEntity(enteredRoom)
        MatcherAssert.assertThat(enteredEntity, CoreMatchers.`is`(expected))
    }
}
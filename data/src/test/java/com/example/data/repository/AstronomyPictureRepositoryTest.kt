package com.example.data.repository

import com.example.data.api.NasaApi
import com.example.data.database.NasaDao
import com.example.data.mapper.JsonMapper
import com.example.data.mapper.RoomMapper
import com.example.data.model.APODJson
import com.example.data.model.APODRoom
import com.example.domain.model.APODEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class AstronomyPictureRepositoryTest {

    companion object {
        private const val TEST_DATE = "2019-10-10"
    }

    private lateinit var enteredEntity: APODEntity
    private lateinit var enteredJson: APODJson
    private lateinit var enteredRoom: APODRoom
    private lateinit var api: NasaApi
    private lateinit var dao: NasaDao
    private lateinit var jsonMapper: JsonMapper
    private lateinit var roomMapper: RoomMapper
    @Before
    fun setUp() {
        enteredEntity = APODEntity(
                "2019-10-10",
                "some explanation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        )
        enteredJson = APODJson(
                "2019-10-10",
                "some explanation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        )
        enteredRoom = APODRoom(
                "2019-10-10",
                "some explanation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        )
        api = Mockito.mock(NasaApi::class.java)
        dao = Mockito.mock(NasaDao::class.java)
        jsonMapper = Mockito.mock(JsonMapper::class.java)
        roomMapper = Mockito.mock(RoomMapper::class.java)
    }

    @Test
    fun testGetAstronomyPictureFetchDao() {
        runBlockingTest {
            Mockito.`when`(dao.getAstronomyPicture(TEST_DATE)).thenReturn(enteredRoom)
            Mockito.`when`(roomMapper.mapToEntity(enteredRoom)).thenReturn(enteredEntity)
            val repository = AstronomyPictureRepository(api, dao, jsonMapper, roomMapper)
            val expected = repository.getAstronomyPicture(TEST_DATE)
            MatcherAssert.assertThat(enteredEntity, CoreMatchers.`is`(expected))

            Mockito.verify(dao).getAstronomyPicture(TEST_DATE)
            Mockito.verifyZeroInteractions(dao)
            Mockito.verifyZeroInteractions(api)
        }
    }

    @Test
    fun testGetAstronomyPictureFetchNetworkApi() {
        runBlockingTest {
            Mockito.`when`(dao.getAstronomyPicture(TEST_DATE)).thenReturn(null)
            Mockito.`when`(api.getAstronomyPicture(TEST_DATE)).thenReturn(enteredJson)
            Mockito.`when`(jsonMapper.mapToEntity(enteredJson)).thenReturn(enteredEntity)
            Mockito.`when`(roomMapper.mapFromEntity(enteredEntity)).thenReturn(enteredRoom)
            val repository = AstronomyPictureRepository(api, dao, jsonMapper, roomMapper)
            val expected = repository.getAstronomyPicture(TEST_DATE)
            MatcherAssert.assertThat(enteredEntity, CoreMatchers.`is`(expected))

            Mockito.verify(dao).getAstronomyPicture(TEST_DATE)
            Mockito.verify(api).getAstronomyPicture(TEST_DATE)
            Mockito.verify(jsonMapper).mapToEntity(enteredJson)
            Mockito.verify(dao).insertAstronomyPicture(enteredRoom)

            Mockito.verifyZeroInteractions(jsonMapper)
            Mockito.verifyZeroInteractions(api)
            Mockito.verifyZeroInteractions(dao)

        }
    }

    @Test
    fun testInsertAstronomyPictureDao() {
        runBlockingTest {
            Mockito.`when`(roomMapper.mapFromEntity(enteredEntity)).thenReturn(enteredRoom)
            val repository = AstronomyPictureRepository(api, dao, jsonMapper, roomMapper)
            repository.insertAstronomyPicture(enteredEntity)

            Mockito.verify(roomMapper).mapFromEntity(enteredEntity)
            Mockito.verify(dao).insertAstronomyPicture(enteredRoom)

            Mockito.verifyZeroInteractions(roomMapper)
            Mockito.verifyZeroInteractions(dao)
        }
    }
}
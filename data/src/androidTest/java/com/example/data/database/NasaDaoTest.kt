package com.example.data.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.data.model.APODRoom
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NasaDaoTest {

    companion object {
        private const val TEST_DATE = "2019-10-10"
        private const val SIZE_DATABASE = 1
    }

    private lateinit var db: NasaDatabase
    private lateinit var entered: APODRoom
    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().context,
                NasaDatabase::class.java)
                .build()
        entered = APODRoom(
                "2019-10-10",
                "some explanation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        )
    }

    @Test
    fun testInsertAstronomyPicture() {
        runBlocking {
            db.nasaDao.insertAstronomyPicture(entered)
            val roomObjectsList = db.nasaDao.getAll()
            val expected = roomObjectsList[roomObjectsList.size - 1]
            Assert.assertEquals(SIZE_DATABASE.toLong(), roomObjectsList.size.toLong())
            MatcherAssert.assertThat(entered, Matchers.`is`(expected))
        }
    }

    @Test
    fun testGetAstronomyPicture() {
        runBlocking {
            db.nasaDao.insertAstronomyPicture(entered)
            val expected = db.nasaDao.getAstronomyPicture(TEST_DATE)
            MatcherAssert.assertThat(entered, Matchers.`is`(expected))
        }
    }

    @After
    fun tearDown() {
        db.close()
    }
}
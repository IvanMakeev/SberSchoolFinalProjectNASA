package com.example.domain.interactor

import com.example.domain.model.APODEntity
import com.example.domain.repository.IAstronomyPictureRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class AstronomyPictureInteractorTest {

    companion object {
        private const val TEST_DATE = "2019-10-10"
    }

    private lateinit var enteredEntity: APODEntity
    private lateinit var interactor: AstronomyPictureInteractor
    private lateinit var repository: IAstronomyPictureRepository
    @Before
    fun setUp() {
        enteredEntity = APODEntity(
                "2019-10-10",
                "some explamation",
                "title",
                "https://apod.nasa.gov/apod/image/",
                "copyright"
        )
        repository = Mockito.mock(IAstronomyPictureRepository::class.java)
        interactor = AstronomyPictureInteractor(repository)
    }

    @Test
    fun testGetAstronomyPicture() {
        runBlockingTest {
            Mockito.`when`(repository.getAstronomyPicture(TEST_DATE)).thenReturn(enteredEntity)
            val actual = interactor.getAstronomyPicture(TEST_DATE)
            Assert.assertEquals(enteredEntity, actual)

            Mockito.verify(repository).getAstronomyPicture(TEST_DATE)

            Mockito.verifyZeroInteractions(repository)
        }
    }

    @Test
    fun testInsertAstronomyPicture() {
        runBlockingTest {
            interactor.insertAstronomyPicture(enteredEntity)

            Mockito.verify(repository).insertAstronomyPicture(enteredEntity)

            Mockito.verifyZeroInteractions(repository)
        }
    }
}
package com.example.presentation.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.exception.CommonException
import com.example.domain.exception.NetworkAccessException
import com.example.domain.interactor.IAstronomyPictureInteractor
import com.example.domain.model.APODEntity
import com.example.presentation.ui.viewmodel.utils.MainCoroutineScopeRule
import com.example.presentation.utils.DateUtils.getDateOffset
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class MainViewModelTest {
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var enteredEntityPicture: APODEntity
    private lateinit var enteredEntityVideo: APODEntity
    private val currentPositionPageAdapter = 0
    private val testDate = getDateOffset(currentPositionPageAdapter)
    private lateinit var interactor: IAstronomyPictureInteractor
    private lateinit var viewModel: MainViewModel
    @Before
    fun setUp() {
        enteredEntityPicture = APODEntity(
                testDate,
                "some explanation",
                "title",
                "https://apod.nasa.gov/apod/image/picture.png",
                "copyright"
        )
        enteredEntityVideo = APODEntity(
                testDate,
                "some explanation",
                "title",
                "https://www.youtube.com/embed/ofZTOxC9JQ4?rel=0",
                "copyright"
        )
        interactor = Mockito.mock(IAstronomyPictureInteractor::class.java)
        viewModel = MainViewModel(interactor, currentPositionPageAdapter)
    }

    @Test
    fun testShowInformationPicture() {
        runBlockingTest {
            Mockito.`when`(interactor.getAstronomyPicture(testDate)).thenReturn(enteredEntityPicture)
            viewModel.showInformation()

            Mockito.verify(interactor).getAstronomyPicture(testDate)

            Mockito.verifyZeroInteractions(interactor)

            Assert.assertEquals(true, viewModel.isLoadingPicture.value)
            Assert.assertEquals(false, viewModel.isLoadingData.value)
            Assert.assertFalse(viewModel.isErrorVisible.get())
            Assert.assertEquals(false, viewModel.isNetworkError.value)
            Assert.assertEquals(true, viewModel.isPictureView.value)
            Assert.assertEquals(enteredEntityPicture.title, viewModel.title.get())
            Assert.assertEquals(enteredEntityPicture.explanation, viewModel.explanation.get())
            Assert.assertEquals(enteredEntityPicture.url, viewModel.urlPicture.get())
            Assert.assertEquals(enteredEntityPicture.copyright, viewModel.copyright.get())
        }
    }

    @Test
    fun testShowInformationVideo() {
        runBlockingTest {
            Mockito.`when`(interactor.getAstronomyPicture(testDate)).thenReturn(enteredEntityVideo)
            viewModel.showInformation()

            Mockito.verify(interactor).getAstronomyPicture(testDate)

            Mockito.verifyZeroInteractions(interactor)

            Assert.assertEquals(true, viewModel.isLoadingPicture.value)
            Assert.assertEquals(false, viewModel.isLoadingData.value)
            Assert.assertFalse(viewModel.isErrorVisible.get())
            Assert.assertEquals(false, viewModel.isNetworkError.value)
            Assert.assertEquals(false, viewModel.isPictureView.value)
            Assert.assertEquals(enteredEntityVideo.title, viewModel.title.get())
            Assert.assertEquals(enteredEntityVideo.explanation, viewModel.explanation.get())
            Assert.assertEquals(enteredEntityVideo.copyright, viewModel.copyright.get())
        }
    }

    @Test
    fun testShowInformationNetworkAccessException() {
        runBlockingTest {
            Mockito.`when`(interactor.getAstronomyPicture(testDate)).thenAnswer { throw NetworkAccessException(Throwable()) }
            viewModel.showInformation()

            Mockito.verify(interactor).getAstronomyPicture(testDate)

            Mockito.verifyZeroInteractions(interactor)

            Assert.assertEquals(true, viewModel.isLoadingPicture.value)
            Assert.assertEquals(false, viewModel.isLoadingData.value)
            Assert.assertTrue(viewModel.isErrorVisible.get())
            Assert.assertEquals(true, viewModel.isNetworkError.value)
            Assert.assertNull(viewModel.title.get())
            Assert.assertNull(viewModel.explanation.get())
            Assert.assertNull(viewModel.urlPicture.get())
            Assert.assertNull(viewModel.copyright.get())
        }
    }

    @Test
    fun testShowInformationCommonException() {
        runBlockingTest {
            Mockito.`when`(interactor.getAstronomyPicture(testDate)).thenAnswer { CommonException(Throwable()) }
            viewModel.showInformation()

            Mockito.verify(interactor).getAstronomyPicture(testDate)

            Mockito.verifyZeroInteractions(interactor)

            Assert.assertEquals(true, viewModel.isLoadingPicture.value)
            Assert.assertEquals(false, viewModel.isLoadingData.value)
            Assert.assertTrue(viewModel.isErrorVisible.get())
            Assert.assertEquals(false, viewModel.isNetworkError.value)
            Assert.assertNull(viewModel.title.get())
            Assert.assertNull(viewModel.explanation.get())
            Assert.assertNull(viewModel.urlPicture.get())
            Assert.assertNull(viewModel.copyright.get())
        }
    }
}
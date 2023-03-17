package com.example.fitpeoassignment

import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fitpeoassignment.core.utils.Resource
import com.example.fitpeoassignment.feature.photolist.data.PhotoRepository
import com.example.fitpeoassignment.feature.photolist.model.Photo
import com.example.fitpeoassignment.feature.photolist.viewmodels.PhotoListViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PhotoListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: PhotoRepository

    @Mock
    private lateinit var connectivityManager: ConnectivityManager

    private lateinit var viewModel: PhotoListViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = PhotoListViewModel(repository, connectivityManager)
    }

    @Test
    fun `loadPhotos should update the photos LiveData when the API call is successful`() = runTest {
        // Arrange
        val photos = listOf(
            Photo(1, "title 1", "url1","url11"),
            Photo(2, "title 2", "url2","url22"),
            Photo(3, "title 2", "url3","url33")
        )
        val successResource = Resource.Success(photos)

        `when`(repository.getPhotos()).thenReturn(photos)
        `when`(connectivityManager.activeNetworkInfo).thenReturn(mock(NetworkInfo::class.java))
        `when`(connectivityManager.activeNetworkInfo?.isConnected).thenReturn(true)

        // Act
        viewModel.loadPhotos()

        // wait for LiveData to update
        delay(500)

        // Assert
        assertEquals(successResource.data, viewModel.photos.value?.data)
    }

     @Test
     fun `loadPhotos should update the photosLiveData when the API call fails`() = runTest {
         // Arrange
         val errorMessage = "An error occurred"
         val errorResource = Resource.Error<List<Photo>>(errorMessage)

         `when`(repository.getPhotos()).thenThrow(MockitoException(errorMessage))
         `when`(connectivityManager.activeNetworkInfo).thenReturn(mock(NetworkInfo::class.java))
         `when`(connectivityManager.activeNetworkInfo?.isConnected).thenReturn(true)

         // Act
         viewModel.loadPhotos()

         // Assert
         assertEquals(errorResource.message, viewModel.photos.value?.message)
     }

     @Test
     fun `loadPhotos should update the photosLiveData when internet connection is not available`() {
         // Arrange
         val errorMessage = "Internet connection not available, Please try after sometime"
         val errorResource = Resource.Error<List<Photo>>(errorMessage)

         `when`(connectivityManager.activeNetworkInfo).thenReturn(null)

         // Act
         viewModel.loadPhotos()

         // Assert
         assertEquals(errorResource.message, viewModel.photos.value?.message)
     }
}
package com.example.fitpeoassignment

import com.example.fitpeoassignment.feature.photolist.data.PhotoApiService
import com.example.fitpeoassignment.feature.photolist.data.PhotoRepository
import com.example.fitpeoassignment.feature.photolist.data.PhotoRepositoryImpl
import com.example.fitpeoassignment.feature.photolist.model.Photo
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException
import org.mockito.Mockito.`when`
import org.mockito.exceptions.base.MockitoException
import kotlin.coroutines.CoroutineContext


@RunWith(MockitoJUnitRunner::class)
class PhotoRepositoryTest {

    @Mock
    private lateinit var photoApi: PhotoApiService

    private lateinit var photoRepository: PhotoRepository

    @Before
    fun setUp() {
        photoRepository = PhotoRepositoryImpl(photoApi)
    }

    @Test
    fun `getPhotos returns expected result on success`() = runBlocking {
        // Arrange
        val expectedPhotos = listOf(
            Photo(1, "title 1", "url1","url11"),
            Photo(2, "title 2", "url2","url22"),
            Photo(3, "title 2", "url3","url33")
        )
        `when`(photoApi.getPhotos()).thenReturn(expectedPhotos)

        // Act
        val result = photoRepository.getPhotos()

        // Assert
        assertTrue(result is List<Photo>)
        assertEquals(expectedPhotos, result)
    }

    @Test
    fun `getPhotos returns expected result on error`() = runBlocking<Unit> {

        val exception = "Error in API"
        `when`(photoApi.getPhotos()).thenThrow(MockitoException(exception))

        assertThrows<Exception> { runBlocking { photoRepository.getPhotos() } }
    }
}
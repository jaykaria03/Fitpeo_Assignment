package com.example.fitpeoassignment.feature.photolist.data

import com.example.fitpeoassignment.feature.photolist.model.Photo
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val apiService: PhotoApiService
) : PhotoRepository {
    override suspend fun getPhotos(): List<Photo> {
        return apiService.getPhotos()
    }
}
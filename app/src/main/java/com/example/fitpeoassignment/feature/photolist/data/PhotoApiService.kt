package com.example.fitpeoassignment.feature.photolist.data

import com.example.fitpeoassignment.feature.photolist.model.Photo
import retrofit2.http.GET

interface PhotoApiService {
    @GET("photos")
    suspend fun getPhotos(): List<Photo>
}
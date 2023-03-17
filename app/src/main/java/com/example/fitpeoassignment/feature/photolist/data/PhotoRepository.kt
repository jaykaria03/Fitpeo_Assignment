package com.example.fitpeoassignment.feature.photolist.data

import com.example.fitpeoassignment.feature.photolist.model.Photo

interface PhotoRepository {
    suspend fun getPhotos(): List<Photo>
}
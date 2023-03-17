package com.example.fitpeoassignment.feature.photolist.viewmodels

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpeoassignment.core.utils.Resource
import com.example.fitpeoassignment.feature.photolist.data.PhotoRepository
import com.example.fitpeoassignment.feature.photolist.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val connectivityManager: ConnectivityManager
) : ViewModel() {

    val isConnected: Boolean
        get() {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    private val _photos = MutableLiveData<Resource<List<Photo>>>()
    val photos: LiveData<Resource<List<Photo>>> = _photos

    fun loadPhotos() {
        viewModelScope.launch {
            try {
                if (isConnected){
                    _photos.value = Resource.Loading()
                    val result = repository.getPhotos()
                    _photos.value = Resource.Success(result)
                }else{
                    _photos.value = Resource.Error("Internet connection not available, Please try after sometime")
                }
            } catch (e: Exception) {
                _photos.value =  Resource.Error(e.message.toString())
            }
        }
    }
}
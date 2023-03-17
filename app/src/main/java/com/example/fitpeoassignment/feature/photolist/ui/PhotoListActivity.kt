package com.example.fitpeoassignment.feature.photolist.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fitpeoassignment.R
import com.example.fitpeoassignment.core.utils.Resource
import com.example.fitpeoassignment.databinding.PhotoListActivityBinding
import com.example.fitpeoassignment.feature.photodetails.PhotoDetailActivity
import com.example.fitpeoassignment.feature.photodetails.PhotoDetailActivity.Companion.PHOTO_DESCRIPTION_KEY
import com.example.fitpeoassignment.feature.photodetails.PhotoDetailActivity.Companion.PHOTO_TITLE_KEY
import com.example.fitpeoassignment.feature.photodetails.PhotoDetailActivity.Companion.PHOTO_URL_KEY
import com.example.fitpeoassignment.feature.photolist.adapter.PhotoListAdapter
import com.example.fitpeoassignment.feature.photolist.model.Photo
import com.example.fitpeoassignment.feature.photolist.viewmodels.PhotoListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Objects

@AndroidEntryPoint
class PhotoListActivity : AppCompatActivity() {

    private val viewModel: PhotoListViewModel by viewModels()

    fun getPhotoViewModel(): PhotoListViewModel {
        return viewModel
    }

    private lateinit var  binding: PhotoListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.photo_list_activity)

        binding.viewModel = viewModel

        val photoItemClick :((Photo)->Unit) = {_photo->
            val intent = Intent(this@PhotoListActivity, PhotoDetailActivity::class.java)
            intent.putExtra(PHOTO_TITLE_KEY, _photo.title)
            intent.putExtra(PHOTO_URL_KEY, _photo.url)
            intent.putExtra(PHOTO_DESCRIPTION_KEY, "")
            startActivity(intent)
        }

        val adapter = PhotoListAdapter(photoItemClick)
        binding.photoList.adapter = adapter

        viewModel.loadPhotos()

        viewModel.photos.observe(this) {
            when(it){
                is Resource.Loading -> {
                    showProgressBar()
                }

                is Resource.Success -> {
                    hideProgressBar()
                    adapter.submitList(it.data)
                }

                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(this@PhotoListActivity,it.message,Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }
}






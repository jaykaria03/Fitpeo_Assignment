package com.example.fitpeoassignment.feature.photodetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitpeoassignment.core.utils.loadImageFromUrl
import com.example.fitpeoassignment.databinding.PhotoDetailActivityBinding
import com.squareup.picasso.Picasso

class PhotoDetailActivity : AppCompatActivity() {

    private lateinit var binding: PhotoDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PhotoDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(PHOTO_TITLE_KEY)
        val url = intent.getStringExtra(PHOTO_URL_KEY)
        val description = intent.getStringExtra(PHOTO_DESCRIPTION_KEY)

        binding.photoTitle.text = title
        binding.photoDescription.text = description

        url?.let { binding.photoImage.loadImageFromUrl(it) }
    }

    companion object {
        const val PHOTO_TITLE_KEY = "photo_title"
        const val PHOTO_URL_KEY = "photo_url"
        const val PHOTO_DESCRIPTION_KEY = "photo_description"
    }
}
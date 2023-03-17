package com.example.fitpeoassignment.core.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.fitpeoassignment.R
import com.squareup.picasso.Picasso

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        if (url.isNullOrEmpty()) {
            view.setImageResource(R.drawable.ic_launcher_background)
        } else {
            view.loadImageFromUrl(url)
        }
    }
}
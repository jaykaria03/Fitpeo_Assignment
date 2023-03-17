package com.example.fitpeoassignment.core.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso


    fun ImageView.loadImageFromUrl(url: String) {
        Picasso.get().load(url).into(this)
    }

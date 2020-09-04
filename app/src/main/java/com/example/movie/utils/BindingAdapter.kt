package com.example.movie.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView)
        .load("https://image.tmdb.org/t/p/w500$url")
        .centerCrop()
        .into(imageView)
}
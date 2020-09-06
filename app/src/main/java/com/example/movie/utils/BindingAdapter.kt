package com.example.movie.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    imageView.load("https://image.tmdb.org/t/p/w500$url") {
        transformations(RoundedCornersTransformation(8f))
    }
//    Glide.with(imageView)
//        .load("https://image.tmdb.org/t/p/w500$url")
//        .centerCrop()
//        .into(imageView)
}
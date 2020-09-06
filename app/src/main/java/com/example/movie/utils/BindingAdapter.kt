package com.example.movie.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.movie.R

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    imageView.load("https://image.tmdb.org/t/p/w500$url") {
        transformations(RoundedCornersTransformation(8f))
        placeholder(R.drawable.filmstrip)
        error(R.drawable.movie_roll)
    }
}
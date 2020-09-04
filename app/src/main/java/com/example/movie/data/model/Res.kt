package com.example.movie.data.model

import com.google.gson.annotations.SerializedName

data class Res(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("results")
    val results: List<Movie>
)
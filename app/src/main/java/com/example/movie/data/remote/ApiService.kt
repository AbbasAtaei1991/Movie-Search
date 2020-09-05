package com.example.movie.data.remote

import com.example.movie.data.model.Detail
import com.example.movie.data.model.Res
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/movie")
    suspend fun search(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Response<Res>

    @GET("movie/{id}")
    suspend fun getDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<Detail>
}
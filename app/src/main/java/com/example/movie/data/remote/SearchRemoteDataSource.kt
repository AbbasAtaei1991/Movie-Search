package com.example.movie.data.remote

import com.example.movie.utils.API_KEY

class SearchRemoteDataSource constructor(
    private val apiService: ApiService
): BaseDataSource() {
    suspend fun search(movie: String) = getResult { apiService.search(API_KEY, movie) }
}
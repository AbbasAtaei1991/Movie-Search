package com.example.movie.data.remote

import com.example.movie.utils.API_KEY

class DetailRemoteDataSource constructor(
    private val apiService: ApiService
): BaseDataSource() {
    suspend fun getDetail(id: Int) = getResult { apiService.getDetail(id, API_KEY) }
}
package com.example.movie.data.repository

import com.example.movie.data.remote.DetailRemoteDataSource

class DetailRepository (
    private val remoteDataSource: DetailRemoteDataSource
) {
    suspend fun getDetail(id: Int) = remoteDataSource.getDetail(id)
}
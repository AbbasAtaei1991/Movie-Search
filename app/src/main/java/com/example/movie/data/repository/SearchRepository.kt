package com.example.movie.data.repository

import com.example.movie.data.remote.SearchRemoteDataSource

class SearchRepository (
    private val remoteDataSource: SearchRemoteDataSource
) {
    suspend fun search(movie: String) = remoteDataSource.search(movie)
}
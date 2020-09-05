package com.example.movie.ui.search

import androidx.lifecycle.*
import com.example.movie.data.model.Res
import com.example.movie.data.repository.SearchRepository
import com.example.movie.utils.Resource
import kotlinx.coroutines.Dispatchers

class SearchViewModel (
    private val searchRepository: SearchRepository
): ViewModel() {
    private val _name = MutableLiveData<String>()

    private val _movies = _name.switchMap { name ->
        search(name)
    }
    val movies: LiveData<Resource<Res?>> = _movies


    fun start(name: String) {
        _name.value = name
    }

    private fun search(movieName: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(searchRepository.search(movieName).data))
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Error Occurred!"))
        }
    }
}
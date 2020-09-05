package com.example.movie.ui.movie

import androidx.lifecycle.*
import com.example.movie.data.model.Detail
import com.example.movie.data.repository.DetailRepository
import com.example.movie.utils.Resource
import kotlinx.coroutines.Dispatchers

class MovieViewModel (
    private val detailRepository: DetailRepository
): ViewModel() {
    private val _id = MutableLiveData<Int>()

    private val _movie = _id.switchMap { id ->
        getMovie(id)
    }
    val movie: LiveData<Resource<Detail?>> = _movie


    fun start(id: Int) {
        _id.value = id
    }

    private fun getMovie(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(detailRepository.getDetail(id).data))
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Error Occurred!"))
        }
    }
}
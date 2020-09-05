package com.example.movie.di.module

import com.example.movie.ui.movie.MovieViewModel
import com.example.movie.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { MovieViewModel(get()) }
}


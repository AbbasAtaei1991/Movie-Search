package com.example.movie.di.module

import com.example.movie.data.repository.SearchRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        SearchRepository(get())
    }
}
package com.example.retrofittest.paging.data.repository

import com.example.retrofittest.paging.data.remote.MovieItem
import com.example.retrofittest.paging.domain.Movie

interface Repository {

    fun fetchMovie(search: String, pageSize: Int): Movie<MovieItem>
}
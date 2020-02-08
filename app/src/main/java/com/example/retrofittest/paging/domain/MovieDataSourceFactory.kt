package com.example.retrofittest.paging.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.retrofittest.paging.data.remote.MovieItem

class MovieDataSourceFactory(private val searchKey: String) : DataSource.Factory<Int, MovieItem>() {

    val itemLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, MovieItem> {
        val movieDataSource = MovieDataSource(searchKey)
        itemLiveDataSource.postValue(movieDataSource)
        return movieDataSource as DataSource<Int, MovieItem>
    }
}
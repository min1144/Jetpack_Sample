package com.example.retrofittest.paging.data.repository

import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.example.retrofittest.paging.data.remote.MovieItem
import com.example.retrofittest.paging.domain.Movie
import com.example.retrofittest.paging.domain.MovieDataSourceFactory

class MovieRepository : Repository {

    override fun fetchMovie(search: String, pageSize: Int): Movie<MovieItem> {
        val sourceFactory = MovieDataSourceFactory(search)

        val livePagedList = sourceFactory.toLiveData(
            pageSize = pageSize
        )

        return Movie(
            pagedList = livePagedList,
            isEmpty = Transformations.switchMap(sourceFactory.itemLiveDataSource) {
                it.isInitialEmpty
            }
        )
    }

}
package com.example.retrofittest.paging.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.retrofittest.paging.data.remote.MovieData
import com.example.retrofittest.paging.data.remote.MovieItem
import com.example.retrofittest.paging.data.repository.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDataSource(private val search: String) : PageKeyedDataSource<Int, MovieItem>() {

    val isInitialEmpty = MutableLiveData<Boolean>()

    companion object {
        const val FIRST_PAGE: Int = 1
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieItem>
    ) {

        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiClient.makeRetrofitService()
                .fetchPosts(query = search, pageCount = FIRST_PAGE.toString())

            if (response.isSuccessful) {
                val movieData = response.body() as MovieData

                isInitialEmpty.postValue(movieData.itemList.isEmpty())

                val nextKey = nextPage(FIRST_PAGE, movieData.display)
                if (!isLast(movieData.total, nextKey)) {
                    callback.onResult(movieData.itemList as ArrayList<MovieItem>, null, nextKey)
                } else {
                    callback.onResult(movieData.itemList as ArrayList<MovieItem>, null, null)
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieItem>) {
//        Log.v(LOG, " ==== loadBefore ===")
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = ApiClient.makeRetrofitService()
//                .fetchPosts(query = SEARCH, pageCount = params.key.toString())
//
//            if(response.isSuccessful) {
//                val movieData = response.body() as MovieData
//
//                val adjustKey = if (params.key > 1) params.key - 1 else null
//                callback.onResult(movieData.itemList as ArrayList<MovieItem>, adjustKey)
//            }
//        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieItem>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiClient.makeRetrofitService()
                .fetchPosts(query = search, pageCount = params.key.toString())

            if (response.isSuccessful) {
                val movieData = response.body() as MovieData

                val nextKey = nextPage(params.key, movieData.display)
                if (!isLast(movieData.total, nextKey)) {
                    callback.onResult(
                        movieData.itemList as ArrayList<MovieItem>,
                        nextPage(params.key, movieData.display)
                    )

                } else {
                    callback.onResult(movieData.itemList as ArrayList<MovieItem>, null)
                }
            }
        }
    }

    val nextPage: (Int, Int) -> Int = { before, start -> before + start }
    private fun isLast(totalCount: Int, compareCount: Int): Boolean = compareCount > totalCount
}
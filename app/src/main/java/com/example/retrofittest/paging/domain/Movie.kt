package com.example.retrofittest.paging.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Movie<T>(
    val pagedList: LiveData<PagedList<T>>,
    val isEmpty: LiveData<Boolean>
)
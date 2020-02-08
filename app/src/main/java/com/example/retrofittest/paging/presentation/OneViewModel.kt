package com.example.retrofittest.paging.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofittest.paging.data.repository.MovieRepository

class OneViewModel(private val repository: MovieRepository) : ViewModel() {

    val searchKey = MutableLiveData<String>()
    private val itemList = map(searchKey) {
        repository.fetchMovie(it, 20)
    }

    val moveList = switchMap(itemList, { it.pagedList })
    val isEmpty = switchMap(itemList, { it.isEmpty })

    class Factory(private val repository: MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OneViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return OneViewModel(repository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
package com.example.retrofittest.tab2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class Tab2ViewModelFactory(val score: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(Tab2ViewModel::class.java)) {
            return Tab2ViewModel(score) as T
        }
        throw IllegalArgumentException("View Model IllegalState")
    }
}
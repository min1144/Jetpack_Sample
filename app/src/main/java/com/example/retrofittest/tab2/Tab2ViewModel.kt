package com.example.retrofittest.tab2

import android.util.Log
import androidx.lifecycle.ViewModel

class Tab2ViewModel(score: Int) : ViewModel() {

    val TAG = this.javaClass.name

    init {
        Log.v(TAG, " init Tab2ViewModel")
    }
}
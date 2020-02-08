package com.example.retrofittest.other

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class Test1ViewModel : ViewModel() {

    private val TAG = this.javaClass.name

    lateinit var list: MutableList<String>
    val score = MutableLiveData<Int>()
    val time = MutableLiveData<Int>()

    val scoreDiff = Transformations.map(score) { diff ->
        "$diff" + "점"
    }

    init {
        Log.v(TAG, " start view model !")
        reset()
        score.value = 0
    }

    override fun onCleared() {
        super.onCleared()
        Log.v(TAG, " finish view model ! ")
    }

    fun reset() {
        list = mutableListOf(
            "aaa",
            "bbb",
            "ccc",
            "ddd"
        )
        list.shuffle()
    }

    fun skip() {
        score.value = (score.value)?.minus(1)
    }

    fun correct() {
        score.value = (score.value)?.plus(1)
    }

    fun clickButton() {
        Log.v(TAG, "click button !")
    }
}
package com.example.exercise4.ui.ppm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PpmViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is ppm Fragment"
    }
    val text: LiveData<String> = _text
}
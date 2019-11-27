package com.example.exercise4.ui.charts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChartsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is charts Fragment"
    }
    val text: LiveData<String> = _text
}
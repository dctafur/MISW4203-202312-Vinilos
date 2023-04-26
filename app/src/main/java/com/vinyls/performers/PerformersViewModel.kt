package com.vinyls.performers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PerformersViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is performers Fragment"
    }
    val text: LiveData<String> = _text
}
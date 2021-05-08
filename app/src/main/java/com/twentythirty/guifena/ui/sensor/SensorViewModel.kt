package com.twentythirty.guifena.ui.sensor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SensorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is sensor Fragment"
    }
    val text: LiveData<String> = _text
}
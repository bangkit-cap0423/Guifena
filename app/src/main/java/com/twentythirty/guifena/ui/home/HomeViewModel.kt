package com.twentythirty.guifena.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twentythirty.guifena.data.MainRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val _textOnline = MutableLiveData<String>().apply {
        value = "0"
    }

    private val _textIncident = MutableLiveData<String>().apply {
        value = "0"
    }

    val onlineText: LiveData<String> = _textOnline
    val incidentText: LiveData<String> = _textIncident


    fun fetchCount() {
        viewModelScope.launch {
            val count = mainRepository.getCount()
            _textOnline.postValue(count.sensorsCount.toString())
            _textIncident.postValue(count.incidentsCount.toString())
        }
    }
}
package com.twentythirty.guifena.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twentythirty.guifena.data.MainRepository
import com.twentythirty.guifena.data.TokenEntity
import com.twentythirty.guifena.utils.Resource
import kotlinx.coroutines.Dispatchers
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
    val allGood = MutableLiveData<Boolean>().apply { value = true }
    val recentIncidents = MutableLiveData<Resource<Any>>()


    fun fetchCount() {
        viewModelScope.launch {
            val count = mainRepository.getCount()
            _textOnline.postValue(count.sensorsCount.toString())
            _textIncident.postValue(count.incidentsCount.toString())
            allGood.postValue(count.allGood)
            try {
                recentIncidents.postValue(Resource.success(data = mainRepository.getRecentIncidents()))
            } catch (exception: Exception) {
                recentIncidents.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )
                )
            }
        }
    }

    fun sendToken(token: String) {
        val tokenEntity = TokenEntity(token = token)
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.sendToken(tokenEntity)
        }
    }
}
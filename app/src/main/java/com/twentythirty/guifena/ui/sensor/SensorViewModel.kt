package com.twentythirty.guifena.ui.sensor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twentythirty.guifena.data.MainRepository
import com.twentythirty.guifena.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SensorViewModel(val mainRepository: MainRepository) : ViewModel() {

    val sensors = MutableLiveData<Resource<Any>>()

    fun getSensors() {
        viewModelScope.launch {
            delay(200L)
            try {
                sensors.postValue(Resource.success(data = mainRepository.getSensors()))
            } catch (exception: Exception) {
                sensors.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )
                )
            }
        }
    }
}
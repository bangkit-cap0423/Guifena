package com.twentythirty.guifena.ui.listIncident

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twentythirty.guifena.data.IncidentEntity
import com.twentythirty.guifena.data.MainRepository
import com.twentythirty.guifena.utils.Resource
import kotlinx.coroutines.launch

/**
 * Created by taufan-mft on 5/29/2021.
 */
class ListIncidentViewModel(private val mainRepository: MainRepository) : ViewModel() {
    val incidents = MutableLiveData<Resource<List<IncidentEntity>>>()

    fun fetchIncidents() {
        viewModelScope.launch {
            try {
                incidents.postValue(Resource.success(data = mainRepository.getAllIncidents()))
            } catch (exception: Exception) {
                incidents.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )
                )
            }
        }
    }
}
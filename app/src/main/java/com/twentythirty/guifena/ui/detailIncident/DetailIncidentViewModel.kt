package com.twentythirty.guifena.ui.detailIncident

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twentythirty.guifena.data.MainRepository
import com.twentythirty.guifena.data.StatusPayload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by taufan-mft on 5/30/2021.
 */
class DetailIncidentViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun changeStatus(status: Int, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val payload = StatusPayload(incidentId = id, status = status)
            mainRepository.changeIncidentStatus(payload)
        }
    }

}
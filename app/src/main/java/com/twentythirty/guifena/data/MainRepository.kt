package com.twentythirty.guifena.data

import com.twentythirty.guifena.network.GuifenaAPI

/**
 * Created by taufan-mft on 5/20/2021.
 */
class MainRepository(private val guifenaAPI: GuifenaAPI) {

    suspend fun getCount() = guifenaAPI.getCount()
    suspend fun getSensors() = guifenaAPI.getSensors()
    suspend fun getRecentIncidents() = guifenaAPI.getRecentIncidents()
    suspend fun sendToken(tokenEntity: TokenEntity) = guifenaAPI.sendToken(tokenEntity)
    suspend fun changeIncidentStatus(statusPayload: StatusPayload) =
        guifenaAPI.changeIncidentStatus(statusPayload)
}
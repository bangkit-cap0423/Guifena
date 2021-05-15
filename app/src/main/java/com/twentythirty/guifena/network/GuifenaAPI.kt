package com.twentythirty.guifena.network

import com.twentythirty.guifena.data.IncidentEntity
import com.twentythirty.guifena.data.SensorEntity
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by taufan-mft on 5/6/2021.
 */
interface GuifenaAPI {
    @GET("sensors")
    suspend fun getSensors(): List<SensorEntity>

    @GET("incidents")
    suspend fun getIncidents(): List<IncidentEntity>

    @GET("sensors/{sensorId}/")
    suspend fun getSensorDetail(@Path("sensorId") sensorId: Int): SensorEntity

    @GET("incidents/{incidentId}/")
    suspend fun getIncidentDetail(@Path("incidentId") incidentId: Int): IncidentEntity
}
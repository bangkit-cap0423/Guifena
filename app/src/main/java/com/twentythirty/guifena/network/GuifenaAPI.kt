package com.twentythirty.guifena.network

import com.twentythirty.guifena.data.CountEntity
import com.twentythirty.guifena.data.IncidentEntity
import com.twentythirty.guifena.data.SensorEntity
import com.twentythirty.guifena.data.TokenEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by taufan-mft on 5/6/2021.
 */
interface GuifenaAPI {
    @GET("sensors")
    suspend fun getSensors(): List<SensorEntity>

    @GET("/")
    suspend fun getCount(): CountEntity

    @GET("incidents")
    suspend fun getIncidents(): List<IncidentEntity>

    @GET("incidents/recents/")
    suspend fun getRecentIncidents(): List<IncidentEntity>

    @GET("sensors/{sensorId}/")
    suspend fun getSensorDetail(@Path("sensorId") sensorId: Int): SensorEntity

    @GET("incidents/{incidentId}/")
    suspend fun getIncidentDetail(@Path("incidentId") incidentId: Int): IncidentEntity

    @POST("token/")
    suspend fun sendToken(@Body tokenEntity: TokenEntity)
}
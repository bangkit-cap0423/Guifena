package com.twentythirty.guifena.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IncidentEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("sensor")
    val sensor: Int,
    @SerializedName("sensor_id")
    val sensorId: Int,
    @SerializedName("sensor_location")
    val sensorLocation: String,
    @SerializedName("sensor_name")
    val sensorName: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("timestamp")
    val timestamp: String
) : Parcelable
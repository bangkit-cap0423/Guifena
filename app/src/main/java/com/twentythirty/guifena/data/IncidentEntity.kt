package com.twentythirty.guifena.data


import com.google.gson.annotations.SerializedName

data class IncidentEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("incident")
    val incident: Int,
    @SerializedName("sensor")
    val sensor: Int,
    @SerializedName("time")
    val time: String
)
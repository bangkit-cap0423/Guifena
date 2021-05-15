package com.twentythirty.guifena.data


import com.google.gson.annotations.SerializedName

data class SensorEntity(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_seen")
    val lastSeen: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("status")
    val status: Int?
)
package com.twentythirty.guifena.data


import com.google.gson.annotations.SerializedName

data class SensorEntity(
    @SerializedName("coordinate")
    val coordinate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("status")
    val status: Int
)
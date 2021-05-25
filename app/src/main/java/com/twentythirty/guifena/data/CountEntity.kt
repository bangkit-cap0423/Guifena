package com.twentythirty.guifena.data


import com.google.gson.annotations.SerializedName

data class CountEntity(
    @SerializedName("incidents_count")
    val incidentsCount: Int,
    @SerializedName("sensors_count")
    val sensorsCount: Int,
    @SerializedName("all_good")
    val allGood: Boolean
)
package com.twentythirty.guifena.data


import com.google.gson.annotations.SerializedName

data class StatusPayload(
    @SerializedName("incident_id")
    val incidentId: Int,
    @SerializedName("status")
    val status: Int
)
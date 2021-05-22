package com.twentythirty.guifena.ui.sensor.dummyData

import com.twentythirty.guifena.data.IncidentEntity

object dummyIncidentData {
    fun setIncident(): List<IncidentEntity> {
        val incident = ArrayList<IncidentEntity>()
        incident.add(
            IncidentEntity(
                1,
                1,
                1,
                "21230.210201.102012",
                "titik 1",
                0,
                "20:30"
            )
        )
        return incident
    }
}
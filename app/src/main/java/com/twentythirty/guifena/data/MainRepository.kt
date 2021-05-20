package com.twentythirty.guifena.data

import com.twentythirty.guifena.network.GuifenaAPI

/**
 * Created by taufan-mft on 5/20/2021.
 */
class MainRepository(private val guifenaAPI: GuifenaAPI) {

    suspend fun getCount() = guifenaAPI.getCount()
    suspend fun getSensors() = guifenaAPI.getSensors()
}
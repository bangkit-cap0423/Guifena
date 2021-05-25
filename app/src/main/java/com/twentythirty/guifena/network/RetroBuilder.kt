package com.twentythirty.guifena.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by taufan-mft on 5/6/2021.
 */
object RetroBuilder {
    private const val BASE_URL = "https://guifena.topanlabs.com"
    var client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS).build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val guifenaApi: GuifenaAPI = getRetrofit().create(GuifenaAPI::class.java)

}
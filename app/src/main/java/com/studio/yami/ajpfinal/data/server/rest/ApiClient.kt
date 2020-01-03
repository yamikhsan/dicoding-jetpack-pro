package com.studio.yami.ajpfinal.data.server.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val baseUrl = "https://api.themoviedb.org/3/"
    fun getService(): ApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
        return retrofit.create(ApiService::class.java)
    }

}
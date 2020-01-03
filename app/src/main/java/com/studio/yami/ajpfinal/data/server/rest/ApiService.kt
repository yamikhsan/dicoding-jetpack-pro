package com.studio.yami.ajpfinal.data.server.rest

import com.studio.yami.ajpfinal.data.server.Film
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/{category}")
    fun getDiscover(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") key: String
    ): Call<Film.Discover>

    @GET("{category}/{id}")
    fun getDetail(
        @Path("category") category: String,
        @Path("id") id: Int,
        @Query("api_key") key: String
    ):Call<Film.Result>

}
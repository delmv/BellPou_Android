package com.henallux.bellpou.remoteDataSource.BellPouAPI

import retrofit2.Call
import retrofit2.http.GET

interface PingService {

    @GET("ping")
    fun ping(): Call<Any>

}
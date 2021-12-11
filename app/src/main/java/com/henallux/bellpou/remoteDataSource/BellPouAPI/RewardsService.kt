package com.henallux.bellpou.remoteDataSource.BellPouAPI

import com.henallux.bellpou.model.Reward
import retrofit2.Call
import retrofit2.http.GET

interface RewardsService {

    @GET("reward")
    fun getRewards(): Call<List<Reward>>

}
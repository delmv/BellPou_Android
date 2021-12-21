package com.henallux.bellpou.remoteDataSource.BellPouAPI

import com.henallux.bellpou.model.BoughtReward
import com.henallux.bellpou.model.PersonalReward
import com.henallux.bellpou.model.Reward
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RewardsService {

    @GET("reward")
    fun getRewards(): Call<List<Reward>>

    @POST("personalReward")
    fun buyReward(@Header("Authorization") token: String, @Body reward: BoughtReward):  Call<PersonalReward>
    
}
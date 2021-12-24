package com.henallux.bellpou.remoteDataSource.bellPouAPI

import com.henallux.bellpou.remoteDataSource.TrashService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object BellPouService {

    private fun createConnectionToApi(): Retrofit = Retrofit.Builder()
                                                        .baseUrl("http://192.168.178.86:3001/")
                                                        .addConverterFactory(MoshiConverterFactory.create().asLenient())
                                                        .build()

    fun trashService() : TrashService = createConnectionToApi().create(TrashService::class.java)

    fun userService(): UserService = createConnectionToApi().create(UserService::class.java)

    fun rewardsService(): RewardsService = createConnectionToApi().create(RewardsService::class.java)

    fun pingService(): PingService = createConnectionToApi().create(PingService::class.java)

}
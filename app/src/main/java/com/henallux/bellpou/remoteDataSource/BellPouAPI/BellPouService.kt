package com.henallux.bellpou.remoteDataSource.BellPouAPI

import com.henallux.bellpou.remoteDataSource.TrashService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class BellPouService {

    companion object {

        private fun createConnectionToApi(): Retrofit = Retrofit.Builder()
                                                            .baseUrl("http://94.105.118.99:3001/")
                                                            .addConverterFactory(MoshiConverterFactory.create().asLenient())
                                                            .build()

        fun trashService() : TrashService = createConnectionToApi().create(TrashService::class.java)

        fun userService(): UserService = createConnectionToApi().create(UserService::class.java)

        fun rewardsService(): RewardsService = createConnectionToApi().create(RewardsService::class.java)

        fun pingService(): PingService = createConnectionToApi().create(PingService::class.java)

    }

}
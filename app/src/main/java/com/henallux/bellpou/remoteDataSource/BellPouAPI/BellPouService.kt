package com.henallux.bellpou.remoteDataSource.BellPouAPI

import com.henallux.bellpou.remoteDataSource.TrashService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory



class BellPouService {
    companion object {
        private fun createConnectionToApi(): Retrofit {
                return Retrofit.Builder()
                    .baseUrl("http://192.168.0.111:3001/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
        }

        fun trashService() : TrashService {
            return createConnectionToApi().create(TrashService::class.java)
        }

        fun userService(): UserService {
            return createConnectionToApi().create(UserService::class.java)
        }

        fun rewardsService(): RewardsService {
            return createConnectionToApi().create(RewardsService::class.java)
        }
    }
}
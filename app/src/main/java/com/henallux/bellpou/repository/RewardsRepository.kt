package com.henallux.bellpou.repository

import android.util.Log
import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.exception.APIConnectionFailedException
import com.henallux.bellpou.exception.APIUnknownException
import com.henallux.bellpou.model.Reward
import com.henallux.bellpou.remoteDataSource.BellPouAPI.BellPouService

class RewardsRepository {
    fun getAllRewards(): List<Reward> {
        val call = BellPouService.rewardsService().getRewards()

        try {
            val response = call.execute()

            println("wtf")

            return response.body() ?: throw APIUnknownException(
                App.applicationContext().getString(R.string.api_unknown_error)
            )
        } catch (e: Exception) {

            Log.e("API ERROR" ,e.message.toString())

            if (e::class.simpleName == "SocketTimeoutException") {
                throw APIConnectionFailedException(
                    App.applicationContext().getString(R.string.api_connection_error)
                )
            }

            throw APIUnknownException(
                App.applicationContext().getString(R.string.api_unknown_error)
            )
        }
    }
}
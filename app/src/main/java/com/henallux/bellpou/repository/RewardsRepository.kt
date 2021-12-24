package com.henallux.bellpou.repository

import android.util.Log
import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.exception.APIConnectionFailedException
import com.henallux.bellpou.exception.APIUnknownException
import com.henallux.bellpou.exception.TooPoorException
import com.henallux.bellpou.model.BoughtReward
import com.henallux.bellpou.model.PersonalReward
import com.henallux.bellpou.model.Reward
import com.henallux.bellpou.remoteDataSource.bellPouAPI.BellPouService
import java.net.ConnectException
import java.net.SocketTimeoutException

object RewardsRepository {


    fun getAllRewards(): List<Reward> {

        val call = BellPouService.rewardsService().getRewards()

        try {

            val response = call.execute()

            if (response.code() == 500 || response.body() == null)
                throw APIUnknownException()

            return response.body()!!

        } catch (e: Exception) {

            Log.e("API ERROR" ,e.message.toString())

            if (e is SocketTimeoutException || e is ConnectException)
                throw APIConnectionFailedException()

            throw APIUnknownException()

        }

    }

    fun buyReward(reward: BoughtReward): PersonalReward {

        val token = UserSharedPreferences.getToken()

        val call = BellPouService.rewardsService().buyReward(token = "Bearer $token", reward)

        try {

            val response = call.execute()

            if (response.code() == 404) throw TooPoorException()

            return response.body() ?: throw APIUnknownException(
                App.applicationContext().getString(R.string.api_unknown_error)
            )

        } catch (e: Exception) {

            Log.e("API ERROR" ,e.message.toString())

            if (e is SocketTimeoutException || e is ConnectException)
                throw APIConnectionFailedException()

            if (e is TooPoorException)
                throw e

            throw APIUnknownException()

        }

    }

}
package com.henallux.bellpou.repository

import android.util.Log
import com.henallux.bellpou.exception.APIConnectionFailedException
import com.henallux.bellpou.remoteDataSource.bellPouAPI.BellPouService

object PingRepository {

    fun ping() {

        val call = BellPouService.pingService().ping()

        try {

            call.execute()

        } catch (e: Exception) {

            Log.e("Ping", e.message.toString())

            throw APIConnectionFailedException()

        }

    }

}
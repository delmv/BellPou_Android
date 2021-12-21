package com.henallux.bellpou.repository

import android.util.Log
import com.henallux.bellpou.exception.APIConnectionFailedException
import com.henallux.bellpou.remoteDataSource.BellPouAPI.BellPouService

class PingRepository {

    fun ping() {

        val call = BellPouService.pingService().ping()

        try {

            call.execute()

        } catch (e: Exception) {

            e.message?.let { Log.w("Ping app", it) }

            throw APIConnectionFailedException()

        }

    }

}
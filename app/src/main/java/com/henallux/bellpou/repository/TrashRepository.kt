package com.henallux.bellpou.repository

import android.util.Log
import com.henallux.bellpou.exception.APIConnectionFailedException
import com.henallux.bellpou.exception.APIUnknownException
import com.henallux.bellpou.exception.NoTrashFoundException
import com.henallux.bellpou.model.Trash
import com.henallux.bellpou.remoteDataSource.BellPouAPI.BellPouService

class TrashRepository {

    fun getTrashesAndLocation() : List<Trash> {

        val call = BellPouService.trashService().getTrashesAndLocations()

        try {

            val response = call.execute()

            if (response.body() == null)
                throw NoTrashFoundException()

            return response.body()!!

        } catch (e: Exception) {

            Log.w("BellPou API - Trash", e)

            if (e::class.simpleName == "SocketTimeoutException")
                throw APIConnectionFailedException()

            if (e::class.simpleName == "NoTrashFoundException")
                throw e

            throw APIUnknownException()

        }
    }

}
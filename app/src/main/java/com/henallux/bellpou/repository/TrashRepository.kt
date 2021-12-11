package com.henallux.bellpou.repository

import android.content.res.Resources
import android.util.Log
import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.exception.APIConnectionFailedException
import com.henallux.bellpou.model.Trash
import com.henallux.bellpou.remoteDataSource.BellPouAPI.BellPouService
import com.henallux.bellpou.exception.APIUnknownException
import kotlin.Exception

class TrashRepository {
    fun getTrashesAndLocation() : List<Trash>? {
        val call = BellPouService.trashService().getTrashesAndLocations()

        try {
            val response = call.execute()
            return response.body()
        } catch (e: Exception) {
            Log.w("BellPou API - Trash", e)
            if (e::class.simpleName == "SocketTimeoutException") {
                throw APIConnectionFailedException(App.applicationContext().getString(R.string.api_connection_error))
            }

            throw APIUnknownException(App.applicationContext().getString(R.string.api_unknown_error))
        }
    }
}
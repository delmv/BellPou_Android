package com.henallux.bellpou.repository

import android.util.Log
import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.exception.APIConnectionFailedException
import com.henallux.bellpou.exception.APIUnknownException
import com.henallux.bellpou.exception.NoTrashFoundException
import com.henallux.bellpou.exception.TrashAlreadyScannedException
import com.henallux.bellpou.model.QR
import com.henallux.bellpou.model.Trash
import com.henallux.bellpou.remoteDataSource.bellPouAPI.BellPouService
import java.net.ConnectException
import java.net.SocketTimeoutException

object TrashRepository {

    fun getTrashesAndLocation() : List<Trash> {

        val call = BellPouService.trashService().getTrashesAndLocations()

        try {

            val response = call.execute()

            if (response.code() == 500)
                throw APIUnknownException()

            if (response.body() == null)
                throw NoTrashFoundException()

            return response.body()!!

        } catch (e: Exception) {

            Log.w("BellPou API - Trash", e)

            if (e is SocketTimeoutException || e is ConnectException)
                throw APIConnectionFailedException()

            if (e is NoTrashFoundException)
                throw e

            throw APIUnknownException()

        }
    }

    fun reportTrash(qrValue: String) {

        val token = UserSharedPreferences.getToken()
        val call = BellPouService.trashService().reportTrash(token = "Bearer $token", QR(qrValue))

        try {

            val response = call.execute()

            if (response.code() == 404)
                throw NoTrashFoundException()

            if (response.code() == 500)
                throw TrashAlreadyScannedException()

        } catch (e: Exception) {

            Log.w("BellPou API - Trash", e.message.toString())

            if (e is SocketTimeoutException || e is ConnectException)
                throw APIConnectionFailedException()

            if (e is NoTrashFoundException)
                throw NoTrashFoundException(App.applicationContext().getString(R.string.no_trash_found_qr))

            if (e is TrashAlreadyScannedException)
                throw e

            throw APIUnknownException()

        }

    }

}
package com.henallux.bellpou.repository

import android.util.Log
import com.henallux.bellpou.exception.APIConnectionFailedException
import com.henallux.bellpou.exception.APIUnknownException
import com.henallux.bellpou.exception.AlreadyRegisteredException
import com.henallux.bellpou.exception.UserNotFoundException
import com.henallux.bellpou.model.LoginForm
import com.henallux.bellpou.model.RegisterForm
import com.henallux.bellpou.model.User
import com.henallux.bellpou.remoteDataSource.bellPouAPI.BellPouService
import java.net.ConnectException
import java.net.SocketTimeoutException

object UserRepository {

    fun login(form: LoginForm): String {

        val call = BellPouService.userService().login(form)

        try {

            val response = call.execute()

            if (response.code() == 404)
                throw UserNotFoundException()

            if (response.code() == 500)
                throw APIUnknownException()

            return response.body().toString()

        } catch (e: Exception) {

            Log.w("BellPou API - Trash", e)

            if (e is SocketTimeoutException || e is ConnectException)
                throw APIConnectionFailedException()

            if (e is UserNotFoundException)
                throw e

            throw APIUnknownException()

        }
    }

    fun register(form: RegisterForm): String {

        val call = BellPouService.userService().register(form)

        try {

            val response = call.execute()

            if (response.code() == 500) throw AlreadyRegisteredException()

            return response.body().toString()

        } catch (e: Exception) {

            Log.w("BellPou API - Trash", e)

            if (e is SocketTimeoutException || e is ConnectException)
                throw APIConnectionFailedException()

            if (e is AlreadyRegisteredException)
                throw e

            throw APIUnknownException()

        }

    }

    fun getUserInformations(): User {

        val token = UserSharedPreferences.getToken()

        val call = BellPouService.userService().getUserInformations(token = "Bearer $token")

        try {

            val response = call.execute()

            if (response.code() == 401 || response.body() == null)
                throw APIUnknownException()

            return response.body()!!

        } catch (e: java.lang.Exception) {

            if (e is SocketTimeoutException || e is ConnectException)
                throw APIConnectionFailedException()

            throw APIUnknownException()

        }
    }

}
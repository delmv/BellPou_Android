package com.henallux.bellpou.repository

import android.util.Log
import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.exception.APIConnectionFailedException
import com.henallux.bellpou.exception.APIUnknownException
import com.henallux.bellpou.exception.AlreadyRegisteredException
import com.henallux.bellpou.exception.UserNotFoundException
import com.henallux.bellpou.model.LoginForm
import com.henallux.bellpou.model.RegisterForm
import com.henallux.bellpou.model.User
import com.henallux.bellpou.remoteDataSource.BellPouAPI.BellPouService
import java.net.ConnectException
import java.net.SocketTimeoutException

class UserRepository {

    fun login(form: LoginForm): String {

        val call = BellPouService.userService().login(form)

        try {

            val response = call.execute()

            if (response.code() == 404)
                throw UserNotFoundException(App.applicationContext().getString(R.string.api_user_not_found))

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

            return response.body() ?: throw APIUnknownException()

        } catch (e: java.lang.Exception) {

            if (e is SocketTimeoutException || e is ConnectException)
                throw APIConnectionFailedException()

            throw APIUnknownException()
        }
    }

}
package com.henallux.bellpou.repository

import android.util.Log
import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.exception.APIConnectionFailedException
import com.henallux.bellpou.exception.APIUnknownException
import com.henallux.bellpou.exception.UserNotFoundException
import com.henallux.bellpou.model.LoginForm
import com.henallux.bellpou.model.RegisterForm
import com.henallux.bellpou.model.User
import com.henallux.bellpou.remoteDataSource.BellPouAPI.BellPouService

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

            if (e::class.simpleName == "SocketTimeoutException") {
                throw APIConnectionFailedException()
            }

            if (e::class.simpleName == "UserNotFoundException") {
                throw e
            }

            throw APIUnknownException()

        }
    }

    fun register(form: RegisterForm): String {

        val call = BellPouService.userService().register(form)

        try {

            val response = call.execute()

            return response.body().toString()

        } catch (e: Exception) {

            Log.w("BellPou API - Trash", e)

            if (e::class.simpleName == "SocketTimeoutException")
                throw APIConnectionFailedException()

            throw APIUnknownException()

        }

    }

    fun getUserInformations(): User {

        val userLogged = UserDBRepository().getUser()

        val call = BellPouService.userService().getUserInformations(token = "Bearer ${userLogged.token}")

        try {

            val response = call.execute()

            return response.body() ?: throw APIUnknownException()

        } catch (e: java.lang.Exception) {

            if (e::class.simpleName == "SocketTimeoutException")
                throw APIConnectionFailedException()

            throw APIUnknownException()
        }
    }

}
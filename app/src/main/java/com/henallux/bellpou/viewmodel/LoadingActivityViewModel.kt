package com.henallux.bellpou.viewmodel

import androidx.lifecycle.ViewModel
import com.henallux.bellpou.exception.NoUserInMemoryException
import com.henallux.bellpou.model.LoginForm
import com.henallux.bellpou.repository.PingRepository
import com.henallux.bellpou.repository.UserRepository
import com.henallux.bellpou.repository.UserSharedPreferences

class LoadingActivityViewModel: ViewModel() {

    fun ping() {

        try {

            PingRepository.ping()

        } catch (e: Exception) {

            throw e

        }

    }

    fun isUserConnected(): Boolean {

        try {

            val user = UserSharedPreferences.getUser()

            UserRepository.login(LoginForm(user.email, user.password))

            return true

        } catch (e: NoUserInMemoryException) {

            return false

        } catch (e: Exception) {

            throw e

        }

    }

}
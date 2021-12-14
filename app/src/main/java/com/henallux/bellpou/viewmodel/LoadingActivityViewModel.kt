package com.henallux.bellpou.viewmodel

import androidx.lifecycle.ViewModel
import com.henallux.bellpou.exception.NoUserInDBException
import com.henallux.bellpou.model.LoginForm
import com.henallux.bellpou.repository.UserDBRepository
import com.henallux.bellpou.repository.UserRepository

class LoadingActivityViewModel: ViewModel() {

    fun isUserConnected(): Boolean {

        try {
            val user = UserDBRepository().getUser()

            UserRepository().login(LoginForm(user.email, user.password))

            return true

        } catch (e: NoUserInDBException) {

            return false

        } catch (e: Exception) {

            throw e

        }

    }

}
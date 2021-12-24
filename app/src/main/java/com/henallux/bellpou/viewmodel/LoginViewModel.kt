package com.henallux.bellpou.viewmodel

import androidx.lifecycle.ViewModel
import com.henallux.bellpou.model.LoggedUser
import com.henallux.bellpou.model.LoginForm
import com.henallux.bellpou.repository.UserRepository
import com.henallux.bellpou.repository.UserSharedPreferences

class LoginViewModel : ViewModel() {

    fun login(form: LoginForm) {

        try {

            val token = UserRepository.login(form)
            val loggedUser = LoggedUser(form.email, form.password, token)

            UserSharedPreferences.insertUser(loggedUser)

        } catch (e: Exception) {

            throw e

        }


    }
}
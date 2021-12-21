package com.henallux.bellpou.viewmodel

import androidx.lifecycle.ViewModel
import com.henallux.bellpou.model.LoggedUser
import com.henallux.bellpou.model.RegisterForm
import com.henallux.bellpou.repository.UserRepository
import com.henallux.bellpou.repository.UserSharedPreferences

class RegisterViewModel: ViewModel() {

    fun register(form: RegisterForm) {

        try {

            val token = UserRepository().register(form)
            UserSharedPreferences.insertUser(LoggedUser(form.email, form.password, token))

        } catch (e: Exception) {

            throw e

        }

    }

}

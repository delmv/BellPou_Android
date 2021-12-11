package com.henallux.bellpou.viewmodel

import androidx.lifecycle.ViewModel
import com.henallux.bellpou.model.LoginForm
import com.henallux.bellpou.repository.UserDBRepository
import com.henallux.bellpou.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppNotLoggedViewModel: ViewModel() {
    fun isUserConnected(): Boolean {
            try {
                val user = UserDBRepository().getUser()

                UserRepository().login(LoginForm(user.email, user.password))

                return true
            } catch (e: Exception) {
                return false
            }
    }
}
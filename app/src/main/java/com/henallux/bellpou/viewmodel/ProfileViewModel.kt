package com.henallux.bellpou.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henallux.bellpou.model.User
import com.henallux.bellpou.repository.UserDBRepository
import com.henallux.bellpou.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ProfileViewModel: ViewModel() {
    var user = MutableLiveData<User>()

    init {
        CoroutineScope(Dispatchers.IO).launch {

            val fetchedUser = UserRepository().getUserInformations()

            withContext(Dispatchers.Main) {
                user.value = fetchedUser
            }
        }
    }

    fun disconnectUser() {
        try {
            UserDBRepository().removeUser()
        } catch (e: Exception) {
            throw e
        }
    }
}
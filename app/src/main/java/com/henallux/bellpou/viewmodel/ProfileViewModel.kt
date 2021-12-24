package com.henallux.bellpou.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henallux.bellpou.model.User
import com.henallux.bellpou.repository.UserRepository
import com.henallux.bellpou.repository.UserSharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel: ViewModel() {
    var user = MutableLiveData<User>()

    suspend fun searchUser() {

        try {

            val fetchedUser = UserRepository.getUserInformations()

            withContext(Dispatchers.Main) {

                user.value = fetchedUser

            }

        } catch (e: Exception) {

            throw e

        }

    }

    fun disconnectUser() {

        UserSharedPreferences.removeUser()

    }
}
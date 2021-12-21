package com.henallux.bellpou.repository

import android.annotation.SuppressLint
import android.content.Context
import com.henallux.bellpou.App
import com.henallux.bellpou.exception.NoTokenFoundInMemoryException
import com.henallux.bellpou.exception.NoUserInDBException
import com.henallux.bellpou.exception.NoUserInMemoryException
import com.henallux.bellpou.model.LoggedUser

class UserSharedPreferences {

    companion object {

        fun insertUser(user: LoggedUser) {

            val context = App.applicationContext()
            val prefs = context.getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
            val editor = prefs.edit()

            editor.putString("email", user.email)
            editor.putString("password", user.password)
            editor.putString("token", user.token)

            editor.apply()

        }

        fun getUser(): LoggedUser {

            val context = App.applicationContext()
            val prefs = context.getSharedPreferences("userPrefs", Context.MODE_PRIVATE)

            val email = prefs.getString("email", "")
            val password = prefs.getString("password", "")
            val token = prefs.getString("token", "")

            if (email == "" || token == "" || password == "" || email == null || password == null || token == null)
                throw NoUserInMemoryException()

            return LoggedUser(email, password, token)

        }

        fun removeUser() {

            val context = App.applicationContext()
            val prefs = context.getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
            val editor = prefs.edit()

            editor.putString("email", "")
            editor.putString("password", "")
            editor.putString("token", "")

            editor.apply()

        }

        fun getToken(): String {

            val context = App.applicationContext()
            val prefs = context.getSharedPreferences("userPrefs", Context.MODE_PRIVATE)

            val token = prefs.getString("token", "")

            if (token == "" || token == null) throw NoTokenFoundInMemoryException()

            return token

        }

    }


}
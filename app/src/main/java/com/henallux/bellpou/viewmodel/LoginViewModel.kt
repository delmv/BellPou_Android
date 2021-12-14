package com.henallux.bellpou.viewmodel

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.model.LoggedUser
import com.henallux.bellpou.model.LoginForm
import com.henallux.bellpou.repository.UserDBRepository
import com.henallux.bellpou.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {

    var isLogged = MutableLiveData<Boolean>()

    fun onButtonLoginClick(view: View) {

        CoroutineScope(Dispatchers.IO).launch {

            val viewgroup = view.rootView
            val email = viewgroup.findViewById<EditText>(R.id.login_email_editText).text.toString()
            val password = viewgroup.findViewById<EditText>(R.id.login_password_editText).text.toString()
            val form = LoginForm(email, password)

            try {

                val token = UserRepository().login(form)
                val loggedUser = LoggedUser(email, password, token)

                UserDBRepository().insertUser(loggedUser)

                withContext(Dispatchers.Main) {

                    isLogged.value = true

                }

            } catch (e: Exception) {

                withContext(Dispatchers.Main) {

                    val toast = Toast.makeText(App.applicationContext(), e.message, Toast.LENGTH_SHORT)
                    toast.show()

                }

            }
        }

    }
}
package com.henallux.bellpou.viewmodel

import android.content.Intent
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.exception.APIUnknownException
import com.henallux.bellpou.exception.PasswordNoMatchException
import com.henallux.bellpou.model.LoggedUser
import com.henallux.bellpou.model.RegisterForm
import com.henallux.bellpou.repository.UserDBRepository
import com.henallux.bellpou.repository.UserRepository
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.*

class RegisterViewModel: ViewModel() {

    // To notify the current fragment to navigate to the logged activity
    var isRegistered = MutableLiveData<Boolean>()

    fun onButtonRegisterClick(view: View) {

        CoroutineScope(Dispatchers.IO).launch {

            val viewGroup = view.rootView

            val firstName = viewGroup.findViewById<ConstraintLayout>(R.id.first_name)
                .findViewById<EditText>(R.id.field).text.toString()

            val lastName = viewGroup.findViewById<ConstraintLayout>(R.id.last_name)
                .findViewById<EditText>(R.id.field).text.toString()

            val email = viewGroup.findViewById<ConstraintLayout>(R.id.email)
                .findViewById<EditText>(R.id.field).text.toString()

            val password = viewGroup.findViewById<ConstraintLayout>(R.id.password)
                .findViewById<EditText>(R.id.field).text.toString()

            val repeatPassword = viewGroup.findViewById<ConstraintLayout>(R.id.repeat_password)
                .findViewById<EditText>(R.id.field).text.toString()

            val birthDateView = viewGroup.findViewById<DatePicker>(R.id.birthdate)

            val birthDate = parseDateFromDatePicker(birthDateView)

            try {

                if (password != repeatPassword) throw PasswordNoMatchException()

                val newUser = RegisterForm(firstName, lastName, email, birthDate, password)
                val token = UserRepository().register(newUser)

                UserDBRepository().insertUser(LoggedUser(email, password, token))

                withContext(Dispatchers.Main) {

                    isRegistered.value = true

                }

            } catch (e: Exception) {

                withContext(Dispatchers.Main) {

                    val toast = Toast.makeText(App.applicationContext(), e.message, Toast.LENGTH_SHORT)
                    toast.show()

                }

            }

        }
    }

    private fun parseDateFromDatePicker(birthDateView: DatePicker): String {

        var parsedDate = ""

        parsedDate += birthDateView.year

        parsedDate += "-"

        if (birthDateView.month + 1 <= 9)
            parsedDate += "0"
        parsedDate += birthDateView.month + 1

        parsedDate += "-"

        if (birthDateView.dayOfMonth <= 9)
            parsedDate += "0"
        parsedDate += birthDateView.dayOfMonth

        return parsedDate

    }
}
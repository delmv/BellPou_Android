package com.henallux.bellpou.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.henallux.bellpou.App
import com.henallux.bellpou.databinding.FragmentRegisterBinding
import com.henallux.bellpou.exception.PasswordNoMatchException
import com.henallux.bellpou.model.RegisterForm
import com.henallux.bellpou.view.activities.LoggedActivity
import com.henallux.bellpou.viewmodel.RegisterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterFragment : Fragment() {

    private val formVM by activityViewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentRegisterBinding.inflate(inflater, container, false).apply {

            registerSubmitButton.setOnClickListener { register(this) }

        }

        return binding.root

    }

    private fun register(binding: FragmentRegisterBinding) {

        val firstName = binding.firstName.field.text.toString()
        val lastName = binding.lastName.field.text.toString()
        val email = binding.email.field.text.toString()
        val password = binding.password.field.text.toString()
        val repeatPassword = binding.repeatPassword.field.text.toString()
        val birthDate = parseDateFromDatePicker(binding.birthdate)

        CoroutineScope(Dispatchers.IO).launch {

            try {

                if (password != repeatPassword) throw PasswordNoMatchException()

                val form = RegisterForm(firstName, lastName, email, birthDate, password)

                formVM.register(form)

                val intent = Intent(activity, LoggedActivity::class.java)
                startActivity(intent)

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
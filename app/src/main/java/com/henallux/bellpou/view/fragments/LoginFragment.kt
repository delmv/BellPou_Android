package com.henallux.bellpou.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.databinding.FragmentLoginBinding
import com.henallux.bellpou.model.LoginForm
import com.henallux.bellpou.view.activities.LoggedActivity
import com.henallux.bellpou.viewmodel.LoadingActivityViewModel
import com.henallux.bellpou.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {

    private val formVM by activityViewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentLoginBinding.inflate(inflater, container, false).apply {

            this.loginButton.setOnClickListener {

                val email = loginEmailEditText.text.toString()
                val password = loginPasswordEditText.text.toString()

                login(LoginForm(email, password), this)

            }

            this.registerButton.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.register_fragment)
            }

        }

        return binding.root

    }

    private fun login(form: LoginForm, binding: FragmentLoginBinding) {

        CoroutineScope(Dispatchers.IO).launch {

            try {

                withContext(Dispatchers.Main) {

                    binding.loginButton.isEnabled = false
                    binding.registerButton.isEnabled = false

                }

                formVM.login(form)

                val intent = Intent(activity, LoggedActivity::class.java)
                startActivity(intent)

            } catch (e: Exception) {

                withContext(Dispatchers.Main) {

                    val toast = Toast.makeText(App.applicationContext(), e.message, Toast.LENGTH_SHORT)
                    toast.show()

                    binding.loginButton.isEnabled = true
                    binding.registerButton.isEnabled = true

                }


            }

        }

    }

}
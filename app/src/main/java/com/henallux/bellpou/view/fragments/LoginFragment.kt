package com.henallux.bellpou.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.databinding.FragmentLoginBinding
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

            this.vm = formVM

            this.registerButton.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.register_fragment)
            }

        }

        formVM.isLogged.observe(viewLifecycleOwner, Observer {
            val intent = Intent(activity, LoggedActivity::class.java)
            this.startActivity(intent)
        })

        return binding.root

    }

}
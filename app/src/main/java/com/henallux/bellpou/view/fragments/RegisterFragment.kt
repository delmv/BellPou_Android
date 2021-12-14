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
import com.henallux.bellpou.App
import com.henallux.bellpou.databinding.FragmentRegisterBinding
import com.henallux.bellpou.view.activities.LoggedActivity
import com.henallux.bellpou.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {

    private val formVM by activityViewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentRegisterBinding.inflate(inflater, container, false).apply {
            this.vm = formVM
        }

       formVM.isRegistered.observe(viewLifecycleOwner, Observer {
           val intent = Intent(activity, LoggedActivity::class.java)
           this.startActivity(intent)
       })

        return binding.root

    }
}
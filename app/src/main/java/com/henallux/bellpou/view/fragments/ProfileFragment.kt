package com.henallux.bellpou.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.henallux.bellpou.R
import com.henallux.bellpou.databinding.FragmentLoginBinding
import com.henallux.bellpou.databinding.FragmentProfileBinding
import com.henallux.bellpou.view.activities.LoggedActivity
import com.henallux.bellpou.viewmodel.LoginViewModel
import com.henallux.bellpou.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private val profileVM by activityViewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentProfileBinding.inflate(inflater, container, false).apply {

            this.vm = profileVM

        }

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root

    }

}
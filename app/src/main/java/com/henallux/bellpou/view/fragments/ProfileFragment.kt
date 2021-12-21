package com.henallux.bellpou.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.henallux.bellpou.App
import com.henallux.bellpou.databinding.FragmentProfileBinding
import com.henallux.bellpou.view.activities.NotLoggedActivity
import com.henallux.bellpou.viewmodel.ProfileViewModel
import kotlinx.coroutines.*

class ProfileFragment : Fragment() {

    private val profileVM by activityViewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentProfileBinding.inflate(inflater, container, false).apply {

            this.vm = profileVM

            logoutButton.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch {

                    try {

                        profileVM.disconnectUser()

                    } catch(e: Exception) {

                        val toast = Toast.makeText(App.applicationContext(), e.message, Toast.LENGTH_SHORT)
                        toast.show()

                    }
                }

                val intent = Intent(activity, NotLoggedActivity::class.java)
                startActivity(intent)

            }

        }

        binding.lifecycleOwner = viewLifecycleOwner

        CoroutineScope(Dispatchers.IO).launch {

            try {

                profileVM.searchUser()

            } catch (e: Exception) {

                withContext(Dispatchers.Main) {

                    val toast = Toast.makeText(App.applicationContext(), e.message, Toast.LENGTH_SHORT)
                    toast.show()

                }

            }

        }



        return binding.root

    }

}
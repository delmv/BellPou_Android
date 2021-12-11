package com.henallux.bellpou.view.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.henallux.bellpou.R
import com.henallux.bellpou.repository.UserDBRepository
import com.henallux.bellpou.viewmodel.AppNotLoggedViewModel
import com.henallux.bellpou.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotLoggedActivity : AppCompatActivity() {

    private val activityVM by viewModels<AppNotLoggedViewModel>()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_logged)

        CoroutineScope(Dispatchers.IO).launch {

            if (activityVM.isUserConnected()) {
                val intent = Intent(applicationContext, LoggedActivity::class.java)
                startActivity(intent)
            }

            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

            navController = navHostFragment.navController
            findViewById<BottomNavigationView>(R.id.bottom_nav).setupWithNavController(navController)
        }

    }

}

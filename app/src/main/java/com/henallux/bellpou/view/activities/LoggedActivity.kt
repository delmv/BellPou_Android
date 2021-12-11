package com.henallux.bellpou.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.henallux.bellpou.R

class LoggedActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

        navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav).setupWithNavController(navController)
    }
}
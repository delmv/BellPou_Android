package com.henallux.bellpou.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.henallux.bellpou.R
import com.henallux.bellpou.repository.PingRepository
import com.henallux.bellpou.viewmodel.LoadingActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoadingActivity : AppCompatActivity() {

    private lateinit var viewModel: LoadingActivityViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        viewModel = ViewModelProvider(this).get(LoadingActivityViewModel::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            try {

                viewModel.ping()

                if (viewModel.isUserConnected()) {

                    withContext(Dispatchers.Main) {

                        val intent = Intent(this@LoadingActivity, LoggedActivity::class.java)
                        this@LoadingActivity.startActivity(intent)

                    }

                } else {
                    withContext(Dispatchers.Main) {

                        val intent = Intent(this@LoadingActivity, NotLoggedActivity::class.java)
                        this@LoadingActivity.startActivity(intent)

                    }
                }

            } catch (e: Exception) {

                withContext(Dispatchers.Main) {
                    findViewById<TextView>(R.id.loading).text = e.message

                }
            }
        }

    }

}
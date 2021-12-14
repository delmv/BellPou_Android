package com.henallux.bellpou.exception

import com.henallux.bellpou.App
import com.henallux.bellpou.R
import kotlin.Exception

class APIConnectionFailedException(message: String = App.applicationContext().getString(R.string.api_connection_error)): Exception(message)

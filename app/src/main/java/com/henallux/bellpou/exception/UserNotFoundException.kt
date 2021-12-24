package com.henallux.bellpou.exception

import com.henallux.bellpou.App
import com.henallux.bellpou.R
import java.lang.Exception

class UserNotFoundException(message: String = App.applicationContext().getString(R.string.api_user_not_found)): Exception(message)
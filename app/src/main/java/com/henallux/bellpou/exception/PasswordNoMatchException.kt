package com.henallux.bellpou.exception

import com.henallux.bellpou.App
import com.henallux.bellpou.R

class PasswordNoMatchException(message: String = App.applicationContext().getString(R.string.password_no_match)): Exception(message)
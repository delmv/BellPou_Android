package com.henallux.bellpou.exception

import com.henallux.bellpou.App
import com.henallux.bellpou.R

class AlreadyRegisteredException(message: String = App.applicationContext().getString(R.string.already_registered)): Exception(message)
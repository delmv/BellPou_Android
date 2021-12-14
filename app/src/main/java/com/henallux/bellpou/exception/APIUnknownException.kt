package com.henallux.bellpou.exception

import com.henallux.bellpou.App
import com.henallux.bellpou.R

class APIUnknownException(message: String = App.applicationContext().getString(R.string.api_unknown_error)): Exception(message)
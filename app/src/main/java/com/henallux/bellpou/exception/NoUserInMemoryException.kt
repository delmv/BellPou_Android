package com.henallux.bellpou.exception

import com.henallux.bellpou.App
import com.henallux.bellpou.R

class NoUserInMemoryException(message: String = App.applicationContext().getString(R.string.no_user_in_memory)): Exception(message)
package com.henallux.bellpou.exception

import com.henallux.bellpou.App
import com.henallux.bellpou.R

class NoTrashFoundException(message: String = App.applicationContext().getString(R.string.no_trash_found)): Exception(message)
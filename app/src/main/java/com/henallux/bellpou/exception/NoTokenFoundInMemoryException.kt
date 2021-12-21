package com.henallux.bellpou.exception

import com.henallux.bellpou.App
import com.henallux.bellpou.R

class NoTokenFoundInMemoryException(message: String = App.applicationContext().getString(R.string.no_token_in_memory)): Exception(message) {
}
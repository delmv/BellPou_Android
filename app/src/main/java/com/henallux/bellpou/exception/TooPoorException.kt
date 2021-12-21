package com.henallux.bellpou.exception

import com.henallux.bellpou.App
import com.henallux.bellpou.R

class TooPoorException(message: String = App.applicationContext().getString(R.string.to_poor_to_buy)): Exception(message)

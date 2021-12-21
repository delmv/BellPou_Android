package com.henallux.bellpou.exception

import com.henallux.bellpou.App
import com.henallux.bellpou.R

class TrashAlreadyScannedException(message: String = App.applicationContext().getString(R.string.trash_already_scanned)): Exception(message) {
}
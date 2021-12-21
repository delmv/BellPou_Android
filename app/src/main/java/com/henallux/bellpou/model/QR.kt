package com.henallux.bellpou.model

import com.squareup.moshi.Json

data class QR(@field:Json(name = "qr_code") val qrCode: String)
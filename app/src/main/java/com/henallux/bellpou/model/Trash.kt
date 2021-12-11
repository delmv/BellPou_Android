package com.henallux.bellpou.model

import com.squareup.moshi.JsonClass

data class Trash(val id: Int, val is_full: Boolean, val nb_alerts: Int, val last_empty: String?, val position: Position)

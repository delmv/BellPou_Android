package com.henallux.bellpou.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Trash(
    @field:Json(name="is_full")val isFull: Boolean,
    @field:Json(name="position")val position: Position)

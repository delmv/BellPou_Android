package com.henallux.bellpou.model

import com.squareup.moshi.Json

data class PersonalReward(
    @field:Json(name = "discount_code") val discountCode: String
)

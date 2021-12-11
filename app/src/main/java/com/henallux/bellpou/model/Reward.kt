package com.henallux.bellpou.model

import com.squareup.moshi.Json

data class Reward(
    @field:Json(name="name_fr") val nameFR: String,
    @field:Json(name="name_en") val nameEN: String,
    @field:Json(name="description_fr") val descriptionFR: String,
    @field:Json(name="description_en") val descriptionEN: String,
    @field:Json(name="throins_cost") val throinsCost: Int,
    @field:Json(name="real_cost") val realCost: Double
)

package com.henallux.bellpou.model

import com.squareup.moshi.Json

data class User(
    @field:Json(name = "first_name") val firstName: String,
    @field:Json(name = "last_name") val lastName: String,
    @field:Json(name = "birth_date") val birthDate: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "nb_throins") val nbThroins: Int)

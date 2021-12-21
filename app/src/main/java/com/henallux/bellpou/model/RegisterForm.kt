package com.henallux.bellpou.model

import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.exception.FieldNotCorrectException
import com.squareup.moshi.Json

class RegisterForm {

    @field:Json(name = "first_name")var firstName: String
        set(value) {

            if (value != "")
                field = value
            else
                throw FieldNotCorrectException(App.applicationContext().getString(R.string.first_name_empty))

        }

    @field:Json(name = "last_name")var lastName: String
        set(value) {

            if (value != "")
                field = value
            else
                throw FieldNotCorrectException(App.applicationContext().getString(R.string.last_name_empty))

        }

    @field:Json(name = "email")var email: String
        set(value) {

            var regex = Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")

            if (regex.matches(value))
                field = value
            else
                throw FieldNotCorrectException(App.applicationContext().getString(R.string.email_not_correct))

        }

    @field:Json(name = "birth_date")var birthDate: String

    @field:Json(name = "password")var password: String
        set(value) {

            if (value != "")
                field = value
            else
                throw FieldNotCorrectException(App.applicationContext().getString(R.string.password_empty))

        }

    constructor(firstName: String,
                lastName: String,
                email: String,
                birthDate: String,
                password: String) {

        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.birthDate = birthDate
        this.password = password

    }

}

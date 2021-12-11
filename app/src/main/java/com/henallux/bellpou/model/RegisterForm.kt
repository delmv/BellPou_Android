package com.henallux.bellpou.model

import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.exception.FieldNotCorrectException

class RegisterForm {

    var first_name: String
        set(value) {
            if (value != "")
                field = value
            else
                throw FieldNotCorrectException(App.applicationContext().getString(R.string.first_name_empty))
        }

    var last_name: String
        set(value) {
            if (value != "")
                field = value
            else
                throw FieldNotCorrectException(App.applicationContext().getString(R.string.last_name_empty))
        }

    var email: String
        set(value) {
            var regex = Regex(".*@.*\\..*")

            if (regex.matches(value))
                field = value
            else
                throw FieldNotCorrectException(App.applicationContext().getString(R.string.email_not_correct))
        }

    var birth_date: String

    var password: String
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

        this.first_name = firstName
        this.last_name = lastName
        this.email = email
        this.birth_date = birthDate
        this.password = password
    }

}

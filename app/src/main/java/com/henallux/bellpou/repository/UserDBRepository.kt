package com.henallux.bellpou.repository

import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.db.BellPouDBImpl
import com.henallux.bellpou.db.LoggedUserEntity
import com.henallux.bellpou.exception.NoUserInDBException
import com.henallux.bellpou.model.LoggedUser

class UserDBRepository {

    fun insertUser(user: LoggedUser) {

        val userEntity = LoggedUserEntity(user.email, user.password, user.token)
        val oldUser = dao.getUser()

        if (oldUser != null)
            dao.deleteUserByEmail(oldUser.email)

        dao.insertUser(userEntity)

    }

    // Tester ce qu'il se passe s'il n'y a aucun utilisateur dans la DB
    fun getUser(): LoggedUser {

        val user = dao.getUser()

        if (user == null)
            throw(NoUserInDBException(App.applicationContext().getString(R.string.no_user_in_db)))

        return LoggedUser(user.email, user.password, user.token)

    }

    fun removeUser() {

        val user = dao.getUser()

        if (user == null)
            throw(NoUserInDBException(App.applicationContext().getString(R.string.no_user_in_db)))

        dao.deleteUserByEmail(user.email)

    }

    companion object {

        private val dao = BellPouDBImpl.getBellPouDao()

    }

}
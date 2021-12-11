package com.henallux.bellpou.db

import androidx.room.*
import com.henallux.bellpou.model.LoggedUser

@Dao
interface BellPouDao {
    @Query("SELECT * FROM user")
    fun getUser(): LoggedUserEntity

    @Insert
    fun insertUser(user: LoggedUserEntity): Long

    @Update
    fun updateUser(user: LoggedUserEntity)

    @Query("DELETE FROM user WHERE email = :email")
    fun deleteUserByEmail(email: String)
}
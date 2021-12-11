package com.henallux.bellpou.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "user")
data class LoggedUserEntity(
    @NonNull @PrimaryKey @ColumnInfo(name="email") val email: String,
    @ColumnInfo(name="password") val password: String,
    @ColumnInfo(name="token") val token: String)

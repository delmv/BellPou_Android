package com.henallux.bellpou.remoteDataSource.bellPouAPI

import com.henallux.bellpou.model.LoginForm
import com.henallux.bellpou.model.RegisterForm
import com.henallux.bellpou.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {

    @POST("user/login")
    fun login(@Body connectionInfos: LoginForm?) : Call<String>

    @POST("client")
    fun register(@Body registerInfos: RegisterForm) : Call<String>

    @GET("user")
    fun getUserInformations(@Header("Authorization") token: String): Call<User>
}
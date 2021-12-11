package com.henallux.bellpou.remoteDataSource

import com.henallux.bellpou.model.Trash
import retrofit2.Call
import retrofit2.http.GET

interface TrashService {

    @GET("trash")
    fun getTrashesAndLocations(): Call<List<Trash>>

}
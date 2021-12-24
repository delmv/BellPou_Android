package com.henallux.bellpou.remoteDataSource

import com.henallux.bellpou.model.QR
import com.henallux.bellpou.model.Trash
import retrofit2.Call
import retrofit2.http.*

interface TrashService {

    @GET("trash")
    fun getTrashesAndLocations(): Call<List<Trash>>

    @POST("trash/scanQR")
    fun reportTrash(@Header("Authorization") token: String, @Body qrCode: QR): Call<String>

}
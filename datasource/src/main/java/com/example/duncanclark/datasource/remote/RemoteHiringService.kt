package com.example.duncanclark.datasource.remote

import com.example.duncanclark.datasource.model.RemoteHiringModel
import retrofit2.Call
import retrofit2.http.GET

interface RemoteHiringService {
    @GET("hiring.json")
    fun getHiringData(): Call<List<RemoteHiringModel>>
}
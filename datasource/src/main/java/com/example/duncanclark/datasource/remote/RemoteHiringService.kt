package com.example.duncanclark.datasource.remote

import com.example.duncanclark.domain.model.RemoteListItem
import retrofit2.Call
import retrofit2.http.GET

interface RemoteHiringService {
    @GET("hiring.json")
    fun getItemListData(): Call<List<RemoteListItem>>
}
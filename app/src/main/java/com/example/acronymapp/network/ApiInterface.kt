package com.example.acronymapp.network

import com.example.acronymapp.model.AcronymResultData
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("software/acromine/dictionary.py")
    fun fetchAllPosts(@Query("sf") sf: String): Call<List<AcronymResultData>>
}
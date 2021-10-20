package com.example.awesomeapp.api

import com.example.awesomeapp.model.PhotosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Aeri on 10/19/2021.
 */
interface ApiService {
    @GET("curated")
    fun getListPhoto(): Call<PhotosResponse>
}
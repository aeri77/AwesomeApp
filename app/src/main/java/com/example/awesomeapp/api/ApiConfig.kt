package com.example.awesomeapp.api

import com.example.awesomeapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Aeri on 10/19/2021.
 */
class ApiConfig {
    companion object{

        private const val AUTHORIZATION = "Authorization"
        private const val BASE_URL = "https://api.pexels.com/v1/"

        fun getApiService(): ApiService {
            val loggingInterceptor = if(BuildConfig.DEBUG)
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) else
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(Interceptor {
                    val request = it.request().newBuilder()
                        .addHeader(AUTHORIZATION, BuildConfig.PEXELS_TOKEN)
                        .build()
                    it.proceed(request)
                })
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
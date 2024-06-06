package com.example.saurabhpracticle.base

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

    //Base Url
    private val baseUrl = "https://reqres.in/api/"
    var apiService : ApiService? = null

    object ApiClient {
        fun getApiService(): ApiService? {
            if (apiService==null){
                // API response interceptor
                val loggingInterceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)

                // Client
                val client = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()

                // Retrofit
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService
        }
}
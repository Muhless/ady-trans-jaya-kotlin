package com.adytransjaya.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit =
        Retrofit
            .Builder()
//            .baseUrl("http://10.0.2.2:8080/api/")
            .baseUrl("http://192.168.3.229:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val api: ApiService = retrofit.create(ApiService::class.java)
}

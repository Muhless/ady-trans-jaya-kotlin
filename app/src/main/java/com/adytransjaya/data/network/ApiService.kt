package com.adytransjaya.data.network

import com.adytransjaya.data.model.LoginRequest
import com.adytransjaya.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest,
    ): Response<LoginResponse>
}

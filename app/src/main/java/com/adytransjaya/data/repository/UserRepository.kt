package com.adytransjaya.data.repository

import com.adytransjaya.data.model.DriverResponse
import com.adytransjaya.data.model.LoginRequest
import com.adytransjaya.data.model.LoginResponse
import com.adytransjaya.data.network.ApiService
import com.adytransjaya.data.preference.TokenPreferences
import retrofit2.Response

class UserRepository(
    private val api: ApiService,
    private val tokenPreferences: TokenPreferences,
) {
    suspend fun login(
        username: String,
        password: String,
    ): Response<LoginResponse> = api.login(LoginRequest(username, password))

    suspend fun saveToken(token: String) {
        tokenPreferences.saveToken(token)
    }

    val tokenFlow = tokenPreferences.tokenFlow

    suspend fun getCurrentDriver(token: String): DriverResponse = api.getCurrentDriver("Bearer $token")
}

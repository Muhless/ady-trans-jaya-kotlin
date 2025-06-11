package com.adytransjaya.data.repository

import com.adytransjaya.data.model.DriverResponse
import com.adytransjaya.data.model.LoginRequest
import com.adytransjaya.data.model.LoginResponse
import com.adytransjaya.data.network.ApiService
import com.adytransjaya.data.preference.TokenPreferences
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository
    @Inject
    constructor(
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

        suspend fun getDriverByUsername(username: String): Response<DriverResponse> = api.getDriverByUsername(username)
    }

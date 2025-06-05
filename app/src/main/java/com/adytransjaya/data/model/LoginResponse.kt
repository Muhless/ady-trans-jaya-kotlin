package com.adytransjaya.data.model

data class LoginResponse(
    val token: String,
    val userId: Int,
    val userName: String,
)

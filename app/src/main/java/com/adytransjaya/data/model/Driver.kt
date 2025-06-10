package com.adytransjaya.data.model

data class Driver(
    val id: Int,
    val name: String,
    val photo: String? = null,
    val phone: String,
    val address: String,
    val username: String,
)

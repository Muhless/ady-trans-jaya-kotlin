package com.adytransjaya.data.model

data class Driver(
    val id: Int,
    val name: String,
    val photo: String? = null,
    val phone: String,
    val address: String,
    val username: String,
) {
    val imageUrl: String?
        get() = photo?.takeIf { it.isNotEmpty() }?.let { "http://192.168.3.229:8080$it" }
}

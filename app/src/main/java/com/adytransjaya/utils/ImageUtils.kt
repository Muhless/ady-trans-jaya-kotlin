package com.adytransjaya.utils

object ImageUtils {
    private const val BASE_URL =
        "http://192.168.3.104:8080/api/driver/username/" // Ganti sesuai IP/server kamu

    fun buildImageUrl(path: String?): String? =
        path?.takeIf { it.isNotBlank() }?.let {
            if (it.startsWith("/")) "$BASE_URL$it" else "$BASE_URL/$it"
        }
}

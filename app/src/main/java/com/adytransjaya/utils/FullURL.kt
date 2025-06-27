package com.adytransjaya.utils

fun toFullUrl(path: String?): String? {
    val baseUrl = "http://192.168.58.229:8080"
    return path?.let { if (it.startsWith("/")) baseUrl + it else it }
}

package com.adytransjaya.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Vehicle(
    val id: Int,
    val name: String,
    val type: String,
    val capacity: String,
    @Json(name = "rate_per_km")
    val ratePerKm: Int,
)

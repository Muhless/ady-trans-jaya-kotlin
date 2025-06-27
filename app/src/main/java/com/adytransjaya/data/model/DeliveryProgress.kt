package com.adytransjaya.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryProgress(
    val id: Int,
    @Json(name = "delivery_start_time")
    val deliveryStartTime: String,
    @Json(name = "pickup_time")
    val pickupTime: String?,
    @Json(name = "pickup_photo_url")
    val pickupPhotoURL: String?,
    @Json(name = "arrival_time")
    val arrivalTime: String?,
    @Json(name = "arrival_photo_url")
    val arrivalPhotoURL: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "updated_at")
    val updatedAt: String?,
)

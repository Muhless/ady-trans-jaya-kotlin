package com.adytransjaya.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryProgress(
    val id: Int,
    @Json(name = "pickup_time")
    val pickupTime: String?,
    @Json(name = "pickup_photo_url")
    val pickupPhotoURL: String?,
    @Json(name = "arrival_time")
    val arrivalTime: String?,
    @Json(name = "receiver_name")
    val receiverName: String?,
    @Json(name = "receiver_phone")
    val receiverPhone: String?,
    @Json(name = "received_at")
    val receivedAt: String?,
    @Json(name = "receiver_signature_url")
    val receiverSignature: String?,
    @Json(name = "delivery_photo_url")
    val deliveryPhotoURL: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "updated_at")
    val updatedAt: String?,
)

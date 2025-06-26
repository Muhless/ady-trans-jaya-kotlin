package com.adytransjaya.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryProgressRequest(
    @Json(name = "delivery_id")
    val deliveryId: Int,
    @Json(name = "delivery_start_time")
    val deliveryStartTime: String? = null,
    @Json(name = "pickup_time")
    val pickupTime: String? = null,
    @Json(name = "pickup_photo_url")
    val pickupPhotoUrl: String? = null,
    @Json(name = "arrival_time")
    val arrivalTime: String? = null,
    @Json(name = "receiver_name")
    val receiverName: String? = null,
    @Json(name = "receiver_phone")
    val receiverPhone: String? = null,
    @Json(name = "received_at")
    val receivedAt: String? = null,
    @Json(name = "receiver_signature_url")
    val receiverSignatureUrl: String? = null,
    @Json(name = "delivery_photo_url")
    val deliveryPhotoUrl: String? = null,
    @Json(name = "delivery_end_time")
    val deliveryEndTime: String? = null,
)

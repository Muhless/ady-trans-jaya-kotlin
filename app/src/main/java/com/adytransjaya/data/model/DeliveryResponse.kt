package com.adytransjaya.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryResponse(
    val data: List<DeliveryItem>,
)

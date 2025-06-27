package com.adytransjaya.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryDetailResponse(
    val data: DeliveryItem,
)

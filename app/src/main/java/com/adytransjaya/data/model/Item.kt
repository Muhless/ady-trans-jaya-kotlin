package com.adytransjaya.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    val id: Int,
    @Json(name = "item_name")
    val itemName: String,
    val quantity: String,
    val weight: Int,
)

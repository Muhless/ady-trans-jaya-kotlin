package com.adytransjaya.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Transaction(
    val id: Int,
    val customer: Customer,
    @Json(name = "total_delivery")
    val totalDelivery: Int,
    val cost: Int,
    @Json(name = "transaction_status")
    val transactionStatus: String,
)

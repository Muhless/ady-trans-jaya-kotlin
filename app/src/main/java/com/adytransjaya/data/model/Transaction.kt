package com.adytransjaya.data.model

data class Transaction(
    val id: Int,
    val customer: Customer,
    val totalDelivery: Int,
    val cost: Int,
    val transactionStatus: String,
)

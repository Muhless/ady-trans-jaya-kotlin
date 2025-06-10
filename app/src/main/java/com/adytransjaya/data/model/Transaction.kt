package com.adytransjaya.data.model

data class Transaction(
    val id: Int,
    val customer_id: Int,
    val customer: Customer,
    val total_delivery: Int,
    val cost: Int,
    val payment_deadline: String,
    val down_payment: Int,
    val down_payment_status: String,
    val down_payment_time: String?,
    val full_payment: Int,
    val full_payment_status: String,
    val full_payment_time: String?,
    val transaction_status: String,
)

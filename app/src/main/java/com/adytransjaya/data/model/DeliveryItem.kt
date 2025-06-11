package com.adytransjaya.data.model

data class DeliveryItem(
    val id: Int,
    val transaction: Transaction,
    val driver: Driver,
    val vehicle: Vehicle,
    val loadType: String,
    val load: String,
    val quantity: String,
    val weight: String,
    val pickupAddress: String,
    val destinationAddress: String,
    val deliveryDate: String,
    val deliveryDeadlineDate: String,
    val deliveryStatus: String,
    val deliveryCost: Int,
)

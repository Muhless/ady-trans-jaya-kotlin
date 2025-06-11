package com.adytransjaya.ui.screen.delivery

import com.adytransjaya.data.model.DeliveryItem

data class DeliveryDetail(
    val invoiceCode: String,
    val customerName: String,
    val phone: String,
    val vehicle: String,
    val deliveryAddress: String,
    val destinationAddress: String,
    val estimatedTime: String,
    val status: String,
)

fun DeliveryItem.toDeliveryDetail(): DeliveryDetail =
    DeliveryDetail(
        invoiceCode = "TRX-${transaction.id}",
        customerName = transaction.customer.name,
        phone = transaction.customer.phone,
        vehicle = vehicle.name,
        deliveryAddress = pickupAddress,
        destinationAddress = destinationAddress,
        estimatedTime = deliveryDeadlineDate,
        status = deliveryStatus,
    )

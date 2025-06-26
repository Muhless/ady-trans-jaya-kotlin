package com.adytransjaya.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryItem(
    val id: Int,
    val transaction: Transaction,
    val driver: Driver,
    val vehicle: Vehicle,
    @Json(name = "delivery_code")
    val deliveryCode: String,
    @Json(name = "load_type")
    val loadType: String,
    @Json(name = "total_weight")
    val totalWeight: String,
    @Json(name = "total_item")
    var totalItem: Int,
    @Json(name = "pickup_address")
    val pickupAddress: String,
    @Json(name = "pickup_address_lat")
    val pickupAddressLat: Double,
    @Json(name = "pickup_address_lang")
    val pickupAddressLang: Double,
    @Json(name = "destination_address")
    val destinationAddress: String,
    @Json(name = "destination_address_lat")
    val destinationAddressLat: Double,
    @Json(name = "destination_address_lang")
    val destinationAddressLang: Double,
    @Json(name = "delivery_date")
    val deliveryDate: String,
    @Json(name = "delivery_deadline_date")
    val deliveryDeadlineDate: String,
    @Json(name = "delivery_status")
    val deliveryStatus: String,
    @Json(name = "delivery_cost")
    val deliveryCost: Int,
    val items: List<Item>,
    @Json(name = "delivery_progress")
    val deliveryProgress: List<DeliveryProgress>,
)

package com.adytransjaya.data.repository

import com.adytransjaya.data.model.DeliveryResponse
import com.adytransjaya.data.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class DeliveryRepository
    @Inject
    constructor(
        private val apiService: ApiService,
    ) {
        suspend fun fetchDeliveries(
            token: String,
            driverId: Int,
        ): Response<DeliveryResponse> = apiService.getDeliveriesByDriverId("Bearer $token", driverId)

        suspend fun getActiveDelivery(
            token: String,
            driverId: Int,
        ): Response<DeliveryResponse> = apiService.getActiveDelivery("Bearer $token", driverId)

        suspend fun updateDelivery(
            token: String,
            driverId: Int,
            status: String,
        ): Response<DeliveryResponse> =
            apiService.updateDelivery(
                "Bearer $token",
                driverId,
                body = mapOf("delivery_status" to status),
            )
    }

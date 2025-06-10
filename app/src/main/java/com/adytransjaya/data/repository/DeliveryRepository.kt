package com.adytransjaya.data.repository

import com.adytransjaya.data.model.DeliveryResponse
import com.adytransjaya.data.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class DeliveryRepository
    @Inject
    constructor(
        private val api: ApiService,
    ) {
        suspend fun getDeliveriesByDriverId(driverId: Int): Response<DeliveryResponse> = api.getDeliveriesByDriverId(driverId)
    }

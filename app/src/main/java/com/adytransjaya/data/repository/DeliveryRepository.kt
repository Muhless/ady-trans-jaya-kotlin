package com.adytransjaya.data.repository

import com.adytransjaya.data.model.DeliveryItem
import com.adytransjaya.data.network.ApiService
import javax.inject.Inject

class DeliveryRepository
    @Inject
    constructor(
        private val apiService: ApiService,
    ) {
        suspend fun getDeliverByDriverId(driverId: Int): List<DeliveryItem> = apiService.getDeliveriesByDriverId(driverId).data
    }

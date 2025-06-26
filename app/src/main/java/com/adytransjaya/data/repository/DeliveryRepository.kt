package com.adytransjaya.data.repository

import android.graphics.Bitmap
import com.adytransjaya.data.model.DeliveryProgressRequest
import com.adytransjaya.data.model.DeliveryResponse
import com.adytransjaya.data.network.ApiService
import com.adytransjaya.utils.PhotoUtils
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
        ): Response<Unit> =
            apiService.updateDelivery(
                "Bearer $token",
                driverId,
                body = mapOf("delivery_status" to status),
            )

        suspend fun createDeliveryProgress(
            auth: String,
            request: DeliveryProgressRequest,
        ): Response<Unit> = apiService.createDeliveryProgress(auth, request)

        suspend fun uploadPickupPhoto(
            deliveryProgressId: Int,
            bitmap: Bitmap,
        ): Result<String> =
            try {
                val photoPart = PhotoUtils.bitmapToMultipart(bitmap, "photo")
                val response = apiService.uploadPickupPhoto(deliveryProgressId, photoPart)

                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!.url)
                } else {
                    Result.failure(Exception("Upload failed: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }

        suspend fun uploadDeliveryPhoto(
            deliveryProgressId: Int,
            bitmap: Bitmap,
        ): Result<String> =
            try {
                val photoPart = PhotoUtils.bitmapToMultipart(bitmap, "photo")
                val response = apiService.uploadDeliveryPhoto(deliveryProgressId, photoPart)

                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!.url)
                } else {
                    Result.failure(Exception("Upload failed: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }

        suspend fun uploadSignature(
            deliveryProgressId: Int,
            bitmap: Bitmap,
        ): Result<String> =
            try {
                val photoPart = PhotoUtils.bitmapToMultipart(bitmap, "photo")
                val response = apiService.uploadSignature(deliveryProgressId, photoPart)

                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!.url)
                } else {
                    Result.failure(Exception("Upload failed: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
    }

package com.adytransjaya.data.network

import com.adytransjaya.data.model.DeliveryDetailResponse
import com.adytransjaya.data.model.DeliveryProgressRequest
import com.adytransjaya.data.model.DeliveryResponse
import com.adytransjaya.data.model.DriverResponse
import com.adytransjaya.data.model.LoginRequest
import com.adytransjaya.data.model.LoginResponse
import com.adytransjaya.data.model.UploadResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @POST("login-driver")
    suspend fun login(
        @Body request: LoginRequest,
    ): Response<LoginResponse>

    @GET("driver/username/{username}")
    suspend fun getDriverByUsername(
        @Path("username") username: String,
    ): Response<DriverResponse>

    @GET("delivery/driver/{id}/active")
    suspend fun getActiveDelivery(
        @Header("Authorization") token: String,
        @Path("id") driverId: Int,
    ): Response<DeliveryResponse>

    @PATCH("delivery/driver/{id}/status")
    suspend fun updateDelivery(
        @Header("Authorization") token: String,
        @Path("id") driverId: Int,
        @Body body: Map<String, String>,
    ): Response<Unit>

    @POST("delivery-progress")
    suspend fun createDeliveryProgress(
        @Header("Authorization") auth: String,
        @Body request: DeliveryProgressRequest,
    ): Response<Unit>

    @Multipart
    @POST("delivery-progress/upload-pickup/{id}")
    suspend fun uploadPickupPhoto(
        @Path("id") id: Int,
        @Part photo: MultipartBody.Part,
    ): Response<UploadResponse>

    @Multipart
    @POST("delivery-progress/upload-delivery/{id}")
    suspend fun uploadDeliveryPhoto(
        @Path("id") id: Int,
        @Part photo: MultipartBody.Part,
    ): Response<UploadResponse>

    @GET("delivery/driver/{id}/history")
    suspend fun getDeliveryHistory(
        @Header("Authorization") token: String,
        @Path("id") driverId: Int,
    ): Response<DeliveryResponse>

    @GET("delivery/{id}")
    suspend fun getDeliveryById(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
    ): Response<DeliveryDetailResponse>
}

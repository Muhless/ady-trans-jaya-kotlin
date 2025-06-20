package com.adytransjaya.data.network

import com.adytransjaya.data.model.DeliveryResponse
import com.adytransjaya.data.model.DriverResponse
import com.adytransjaya.data.model.LoginRequest
import com.adytransjaya.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
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

    @GET("deliveries/driver/{driverId}")
    suspend fun getDeliveriesByDriverId(
        @Header("Authorization") token: String,
        @Path("driverId") driverId: Int,
    ): Response<DeliveryResponse>

    @GET("delivery/driver/{id}/active")
    suspend fun getActiveDelivery(
        @Header("Authorization") token: String,
        @Path("id") driverId: Int,
    ): Response<DeliveryResponse>
}

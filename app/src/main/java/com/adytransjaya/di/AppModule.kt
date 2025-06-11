package com.adytransjaya.di

import android.content.Context
import android.content.SharedPreferences
import com.adytransjaya.data.network.ApiService
import com.adytransjaya.data.preference.TokenPreferences
import com.adytransjaya.data.repository.DeliveryRepository
import com.adytransjaya.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context,
    ): SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideTokenPreferences(sharedPreferences: SharedPreferences): TokenPreferences = TokenPreferences(sharedPreferences)

    @Provides
    @Singleton
    fun provideApiService(): ApiService =
        Retrofit
            .Builder()
            .baseUrl("http://192.168.50.229:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(
        apiService: ApiService,
        tokenPreferences: TokenPreferences,
    ): UserRepository = UserRepository(apiService, tokenPreferences)

    @Provides
    @Singleton
    fun provideDeliveryRepository(apiService: ApiService): DeliveryRepository = DeliveryRepository(apiService)
}

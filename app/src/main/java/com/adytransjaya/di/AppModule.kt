package com.adytransjaya.di

import android.content.Context
import android.content.SharedPreferences
import com.adytransjaya.data.network.ApiService
import com.adytransjaya.data.preference.TokenPreferences
import com.adytransjaya.data.repository.DeliveryRepository
import com.adytransjaya.data.repository.UserRepository
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    fun provideApiService(): ApiService {
        val logging =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        val client =
            OkHttpClient
                .Builder()
                .addInterceptor(logging)
                .build()

        val moshi =
            Moshi
                .Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        return Retrofit
            .Builder()
            .baseUrl("http://192.168.135.229:8080/api/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }

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

package com.adytransjaya.ui.screen.delivery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.adytransjaya.data.model.DeliveryItem
import com.adytransjaya.data.model.DeliveryProgressRequest
import com.adytransjaya.data.preference.UserPreference
import com.adytransjaya.data.repository.DeliveryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeliveryViewModel
    @Inject
    constructor(
        private val deliveryRepository: DeliveryRepository,
        application: Application,
    ) : AndroidViewModel(application) {
        private val context = application.applicationContext

        private val _activeDelivery = MutableStateFlow<DeliveryItem?>(null)
        private val _deliveryHistory = MutableStateFlow<List<DeliveryItem>>(emptyList())
        private val _getDeliveryById = MutableStateFlow<DeliveryItem?>(null)
        private val _isLoading = MutableStateFlow(false)
        private val _errorMessage = MutableStateFlow<String?>(null)

        val activeDelivery: StateFlow<DeliveryItem?> = _activeDelivery.asStateFlow()
        val deliveryHistory: StateFlow<List<DeliveryItem>> = _deliveryHistory
        val getDeliveryById: StateFlow<DeliveryItem?> = _getDeliveryById
        val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
        val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

        fun getActiveDelivery() {
            viewModelScope.launch {
                _isLoading.value = true
                _errorMessage.value = null

                try {
                    val token = UserPreference.getToken(context)
                    val driverId = UserPreference.getDriverId(context)

                    if (token == null || driverId == null) {
                        _errorMessage.value = "Token atau ID pengemudi tidak ditemukan"
                        return@launch
                    }

                    val response = deliveryRepository.getActiveDelivery("Bearer $token", driverId)
                    if (response.isSuccessful) {
                        val delivery = response.body()?.data?.firstOrNull()
                        if (delivery != null) {
                            _activeDelivery.value = delivery
                        }
                    } else {
                        _errorMessage.value = "Gagal ambil data (${response.code()})"
                    }
                } catch (e: Exception) {
                    _errorMessage.value = "Error: ${e.localizedMessage ?: "Terjadi kesalahan"}"
                } finally {
                    _isLoading.value = false
                }
            }
        }

        fun updateDelivery(
            id: Int,
            status: String,
            onSuccess: () -> Unit = {},
        ) {
            viewModelScope.launch {
                _isLoading.value = true
                _errorMessage.value = null
                try {
                    val token = UserPreference.getToken(context)
                    if (token == null) {
                        _errorMessage.value = "Token tidak tersedia"
                    }
                    val response = deliveryRepository.updateDelivery("Bearer $token", id, status)
                    if (response.isSuccessful) {
                        onSuccess()
                        getActiveDelivery()
                    } else {
                        _errorMessage.value = "Gagal memulai pengiriman (${response.code()}"
                    }
                } catch (e: Exception) {
                    _errorMessage.value = "Error: (${e.localizedMessage ?: "Terjadi kesalahan"}"
                } finally {
                    _isLoading.value = false
                }
            }
        }

        fun createDeliveryProgress(
            request: DeliveryProgressRequest,
            onSuccess: () -> Unit = {},
        ) {
            viewModelScope.launch {
                _isLoading.value = true
                _errorMessage.value = null

                try {
                    val token = UserPreference.getToken(context)
                    if (token == null) {
                        _errorMessage.value = "Token tidak tersedia"
                        return@launch
                    }

                    val response = deliveryRepository.createDeliveryProgress("Bearer $token", request)

                    if (response.isSuccessful) {
                        onSuccess()
                        getActiveDelivery()
                    } else {
                        _errorMessage.value = "Gagal membuat progress (${response.code()})"
                    }
                } catch (e: Exception) {
                    _errorMessage.value = "Terjadi kesalahan: ${e.localizedMessage ?: "Unknown error"}"
                } finally {
                    _isLoading.value = false
                }
            }
        }

        fun getDeliveryHistory() {
            viewModelScope.launch {
                _isLoading.value = true
                _errorMessage.value = null

                try {
                    val token = UserPreference.getToken(context)
                    val driverId = UserPreference.getDriverId(context)

                    if (token == null || driverId == null) {
                        _errorMessage.value = "Token atau ID pengemudi tidak ditemukan"
                        return@launch
                    }

                    val response = deliveryRepository.getDeliveryHistory("Bearer $token", driverId)
                    if (response.isSuccessful) {
                        _deliveryHistory.value = response.body()?.data ?: emptyList()
                    } else {
                        _errorMessage.value = "Gagal mengambil riwayat: ${response.code()}"
                    }
                } catch (e: Exception) {
                    _errorMessage.value = "Error: ${e.localizedMessage ?: "Terjadi kesalahan"}"
                } finally {
                    _isLoading.value = false
                }
            }
        }

        fun getDeliveryById(id: Int) {
            viewModelScope.launch {
                _isLoading.value = true
                try {
                    val token = UserPreference.getToken(context) ?: return@launch
                    val response = deliveryRepository.getDeliveryById("Bearer $token", id)
                    if (response.isSuccessful) {
                        _getDeliveryById.value = response.body()?.data
                    } else {
                        _errorMessage.value = "Gagal ambil data: ${response.code()}"
                    }
                } catch (e: Exception) {
                    _errorMessage.value = "Error: ${e.localizedMessage}"
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }

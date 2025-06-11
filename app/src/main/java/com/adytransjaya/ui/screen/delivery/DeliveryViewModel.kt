package com.adytransjaya.ui.screen.delivery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.adytransjaya.data.model.DeliveryItem
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

        private val _deliveries = MutableStateFlow<List<DeliveryItem>>(emptyList())
        private val _isLoading = MutableStateFlow(false)
        private val _errorMessage = MutableStateFlow<String?>(null)

        val deliveries: StateFlow<List<DeliveryItem>> = _deliveries.asStateFlow()
        val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
        val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

        private val _deliveryHistory = MutableStateFlow<List<DeliveryItem>>(emptyList())
        private val _isLoadingHistory = MutableStateFlow(false)
        private val _historyErrorMessage = MutableStateFlow<String?>(null)

        val deliveryHistory: StateFlow<List<DeliveryItem>> = _deliveryHistory.asStateFlow()
        val isLoadingHistory: StateFlow<Boolean> = _isLoadingHistory.asStateFlow()
        val historyErrorMessage: StateFlow<String?> = _historyErrorMessage.asStateFlow()

        fun getDeliveries() {
            viewModelScope.launch {
                _isLoading.value = true
                _errorMessage.value = null

                try {
                    val token = UserPreference.getToken(context)
                    val driverId = UserPreference.getDriverId(context)

                    when {
                        token == null -> {
                            _errorMessage.value = "Token tidak tersedia"
                            return@launch
                        }

                        driverId == null -> {
                            _errorMessage.value = "Driver ID tidak tersedia"
                            return@launch
                        }

                        else -> {
                            val response = deliveryRepository.fetchDeliveries("Bearer $token", driverId)
                            if (response.isSuccessful) {
                                _deliveries.value = response.body()?.data ?: emptyList()
                            } else {
                                _errorMessage.value =
                                    when (response.code()) {
                                        401 -> "Unauthorized - Token tidak valid"
                                        404 -> "Data tidak ditemukan"
                                        500 -> "Server error"
                                        else -> "Gagal ambil data: ${response.code()}"
                                    }
                            }
                        }
                    }
                } catch (e: Exception) {
                    _errorMessage.value = "Error: ${e.localizedMessage ?: "Terjadi kesalahan"}"
                } finally {
                    _isLoading.value = false
                }
            }
        }

        fun clearError() {
            _errorMessage.value = null
        }

        fun refreshDeliveries() {
            getDeliveries()
        }
    }

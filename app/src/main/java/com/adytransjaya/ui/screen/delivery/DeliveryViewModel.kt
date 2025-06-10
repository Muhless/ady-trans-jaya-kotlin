package com.adytransjaya.ui.screen.delivery

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adytransjaya.data.repository.DeliveryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeliveryViewModel
    @Inject
    constructor(
        private val deliveryRepository: DeliveryRepository,
    ) : ViewModel() {
        private val _deliveries = mutableStateOf<List<DeliveryItem>>(emptyList())
        val deliveries: State<List<DeliveryItem>> = _deliveries

        var isLoading = mutableStateOf(false)
            private set
        var errorMessage = mutableStateOf<String?>(null)
            private set

        fun fetchDeliveries(driverId: Int) {
            isLoading.value = true
            errorMessage.value = null

            viewModelScope.launch {
                try {
                    val response = deliveryRepository.getDeliveriesByDriverId(driverId)
                    if (response.isSuccessful) {
                        _deliveries.value = response.body()?.data ?: emptyList()
                    } else {
                        errorMessage.value = "Gagal memuat data: ${response.code()}"
                    }
                } catch (e: Exception) {
                    errorMessage.value = "Terjadi kesalahan: ${e.localizedMessage}"
                } finally {
                    isLoading.value = false
                }
            }
        }
    }

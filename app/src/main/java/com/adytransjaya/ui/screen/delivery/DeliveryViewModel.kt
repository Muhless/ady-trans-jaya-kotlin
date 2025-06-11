package com.adytransjaya.ui.screen.delivery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adytransjaya.data.model.DeliveryItem
import com.adytransjaya.data.repository.DeliveryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeliveryViewModel
    @Inject
    constructor(
        private val deliveryRepository: DeliveryRepository,
    ) : ViewModel() {
        private val _deliveries = MutableStateFlow<List<DeliveryItem>>(emptyList())
        val deliveries: StateFlow<List<DeliveryItem>> = _deliveries

        private val _currentDelivery = MutableStateFlow<DeliveryItem?>(null)
        val currentDelivery: StateFlow<DeliveryItem?> = _currentDelivery

        fun loadDeliveriesByDriverId(driverId: Int) {
            viewModelScope.launch {
                val result = deliveryRepository.getDeliverByDriverId(driverId)
                _deliveries.value = result
            }
        }

        fun loadCurrentDelivery(driverId: Int) {
            viewModelScope.launch {
                val deliveries = deliveryRepository.getDeliverByDriverId(driverId)
                _currentDelivery.value = deliveries.firstOrNull { it.deliveryStatus == "ON_GOING" }
            }
        }
    }

package com.adytransjaya.ui.screen.delivery

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.adytransjaya.data.preference.UserPreference

@Composable
fun deliveryScreen(
    navController: NavController,
    deliveryViewModel: DeliveryViewModel,
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val driverId = UserPreference.getDriverId(context)
        deliveryViewModel.loadCurrentDelivery(driverId) // ← ini yang penting
    }

    val currentDelivery by deliveryViewModel.currentDelivery.collectAsState()

    currentDelivery?.let { delivery ->
        Column {
            Text("Nama: ${delivery.transaction.customer.name}")
            Text("Tujuan: ${delivery.destinationAddress}")
            Text("Status: ${delivery.deliveryStatus}")
        }
    } ?: Text("Tidak ada pengiriman aktif.")
}

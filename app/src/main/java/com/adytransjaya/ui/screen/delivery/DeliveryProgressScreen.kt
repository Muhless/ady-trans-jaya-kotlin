package com.adytransjaya.ui.screen.delivery

import DeliveryProgressCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryProgressScreen(
    navController: NavController,
    deliveryViewModel: DeliveryViewModel,
) {
    val delivery by deliveryViewModel.selectedDelivery.collectAsState()
    val isLoading by deliveryViewModel.isLoading.collectAsState()
    val errorMessage by deliveryViewModel.errorMessage.collectAsState()

    when {
        isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }

        errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Error: $errorMessage",
                    modifier = Modifier.padding(16.dp),
                )
            }
        }

        else -> {
            delivery?.let { nonNullDelivery ->
                val progressList = nonNullDelivery.deliveryProgress.orEmpty()

                if (progressList.isNotEmpty()) {
                    LazyColumn(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(progressList) { progress ->
                            DeliveryProgressCard(progress = progress, delivery = nonNullDelivery)
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Belum ada progress pengiriman",
                            modifier = Modifier.padding(16.dp),
                        )
                    }
                }
            } ?: Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Data pengiriman tidak ditemukan",
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}

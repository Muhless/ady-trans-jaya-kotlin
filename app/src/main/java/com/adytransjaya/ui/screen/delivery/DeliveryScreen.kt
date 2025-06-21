package com.adytransjaya.ui.screen.delivery

import DeliveryDetailCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryScreen(
    navController: NavController,
    deliveryViewModel: DeliveryViewModel,
) {
    val delivery by deliveryViewModel.activeDelivery.collectAsState()
    val isLoading by deliveryViewModel.isLoading.collectAsState()
    val errorMessage by deliveryViewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        deliveryViewModel.getActiveDelivery()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }

            errorMessage != null -> {
                Text(
                    text = errorMessage ?: "Terjadi kesalahan",
                    modifier =
                        Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                    color = MaterialTheme.colorScheme.error,
                )
            }

            delivery != null -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding =
                        androidx.compose.foundation.layout
                            .PaddingValues(16.dp),
                ) {
                    item {
                        DeliveryDetailCard(delivery = delivery!!)
                    }
                }
            }

            else -> {
                Text(
                    text = "Tidak ada pengiriman",
                    modifier =
                        Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

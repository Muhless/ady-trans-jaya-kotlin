package com.adytransjaya.ui.screen.delivery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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
import com.adytransjaya.data.model.DeliveryItem

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryScreen(
    navController: NavController,
    deliveryViewModel: DeliveryViewModel,
) {
    val deliveries by deliveryViewModel.deliveries.collectAsState()
    val isLoading by deliveryViewModel.isLoading.collectAsState()
    val errorMessage by deliveryViewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        deliveryViewModel.getDeliveries()
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

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding =
                        androidx.compose.foundation.layout
                            .PaddingValues(16.dp),
                ) {
                    items(deliveries) { delivery ->
                        deliveryItem(delivery = delivery)
                    }
                }
            }
        }
    }
}

@Composable
private fun deliveryItem(delivery: DeliveryItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = "Delivery ID: ${delivery.id}",
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Driver: ${delivery.driver.name}",
                style = MaterialTheme.typography.bodyMedium,
                color =
                    when (delivery.deliveryStatus?.lowercase()) {
                        "completed" -> MaterialTheme.colorScheme.primary
                        "in_progress" -> MaterialTheme.colorScheme.secondary
                        "pending" -> MaterialTheme.colorScheme.tertiary
                        else -> MaterialTheme.colorScheme.onSurface
                    },
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Pickup: ${delivery.pickupAddress}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Destination: ${delivery.destinationAddress}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Load: ${delivery.load} (${delivery.loadType})",
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Quantity: ${delivery.quantity} | Weight: ${delivery.weight}",
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Delivery Date: ${delivery.deliveryDate}",
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Cost: Rp ${delivery.deliveryCost}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

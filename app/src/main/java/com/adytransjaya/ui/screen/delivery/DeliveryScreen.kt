package com.adytransjaya.ui.screen.delivery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.adytransjaya.ui.components.card.delivery.DeliveryDetailCard
import com.adytransjaya.ui.theme.AppColors

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
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    item {
                        DeliveryDetailCard(delivery = delivery!!)
                    }
                    delivery?.let {
                        if (it.deliveryStatus == "menunggu pengemudi") {
                            item {
                                Button(
                                    modifier =
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                                    onClick = {
                                        delivery?.let {
                                            deliveryViewModel.updateDelivery(
                                                it.id,
                                                "dalam pengiriman",
                                            )
                                        }
                                    },
                                    colors =
                                        ButtonDefaults.buttonColors(
                                            containerColor = AppColors.BrandBlue,
                                            contentColor = AppColors.base,
                                        ),
                                    shape = RoundedCornerShape(10.dp),
                                ) {
                                    Text("Mulai Pengiriman")
                                }
                            }
                        }
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

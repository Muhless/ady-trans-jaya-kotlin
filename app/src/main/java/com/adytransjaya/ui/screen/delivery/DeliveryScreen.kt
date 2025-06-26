package com.adytransjaya.ui.screen.delivery

import ConfirmationDialog
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adytransjaya.ui.components.card.delivery.DeliveryDetailCard
import com.adytransjaya.ui.theme.AppColors
import kotlinx.coroutines.launch

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryScreen(
    navController: NavController,
    deliveryViewModel: DeliveryViewModel,
) {
    val delivery by deliveryViewModel.activeDelivery.collectAsState()
    val isLoading by deliveryViewModel.isLoading.collectAsState()
    val errorMessage by deliveryViewModel.errorMessage.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        deliveryViewModel.getActiveDelivery()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                snackbarHostState,
                modifier = Modifier.padding(bottom = 20.dp),
            ) { data ->
                Snackbar(
                    containerColor = Color(0xFFB9F6CA),
                    contentColor = Color.Black,
                ) {
                    Text(
                        text = data.visuals.message,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        },
    ) {
        Box(
            modifier =
                Modifier
                    .padding(it)
                    .fillMaxSize(),
        ) {
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
                            item {
                                if (it.deliveryStatus == "menunggu pengemudi") {
                                    Button(
                                        modifier =
                                            Modifier
                                                .fillMaxWidth()
                                                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                                        onClick = { showDialog = true },
                                        colors =
                                            ButtonDefaults.buttonColors(
                                                containerColor = AppColors.BrandBlue,
                                                contentColor = AppColors.base,
                                            ),
                                        shape = RoundedCornerShape(10.dp),
                                    ) {
                                        Text("Mulai Pengiriman")
                                    }
                                } else if (it.deliveryStatus == "dalam pengiriman") {
                                    Button(
                                        modifier =
                                            Modifier
                                                .fillMaxWidth()
                                                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                                        onClick = {
                                            delivery?.let {
                                                navController.navigate("delivery-progress/${it.id}")
                                            }
                                        },
                                        colors =
                                            ButtonDefaults.buttonColors(
                                                containerColor = AppColors.Success,
                                                contentColor = AppColors.base,
                                            ),
                                    ) {
                                        Text("Progress Pengiriman")
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

        if (showDialog) {
            ConfirmationDialog(
                showDialog = showDialog,
                onDismiss = { showDialog = false },
                onConfirm = {
                    showDialog = false
                    delivery?.let {
                        deliveryViewModel.updateDelivery(
                            it.id,
                            "dalam pengiriman",
                            onSuccess = {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        "Pengiriman dimulai, silahkan selesaikan pengiriman sebelum batas waktu yang ditentukan",
                                    )
                                    navController.navigate("delivery-progress/${it.id}")
                                }
                            },
                        )
                    }
                },
                title = "Konfirmasi",
                message = "Apakah Anda yakin ingin memulai pengiriman ini?",
            )
        }
    }
}

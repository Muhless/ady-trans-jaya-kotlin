package com.adytransjaya.ui.screen.history

import DeliveryHistoryCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adytransjaya.ui.screen.delivery.DeliveryViewModel
import com.adytransjaya.ui.theme.AppColors

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryHistoryScreen(
    navController: NavController,
    deliveryViewModel: DeliveryViewModel,
) {
    val deliveryHistories by deliveryViewModel.deliveryHistory.collectAsState()
    val isLoading by deliveryViewModel.isLoading.collectAsState()
    val errorMessage by deliveryViewModel.errorMessage.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val showSnackbar =
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.get<Boolean>("showSnackbarAfterNavigate") == true

    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            snackbarHostState.showSnackbar("Pengiriman diselesaikan")
            navController.currentBackStackEntry
                ?.savedStateHandle
                ?.set("showSnackbarAfterNavigate", false)
        }
    }

    LaunchedEffect(Unit) {
        deliveryViewModel.getDeliveryHistory()
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
    ) { paddingValues ->
        Box(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(AppColors.background),
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier =
                    Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Kembali",
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }

            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                    )
                }

                errorMessage != null -> {
                    Text(
                        text = "Error: $errorMessage",
                        modifier =
                            Modifier
                                .align(Alignment.Center)
                                .padding(16.dp),
                        color = MaterialTheme.colorScheme.error,
                    )
                }

                !deliveryHistories.isNullOrEmpty() -> {
                    LazyColumn(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(top = 56.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(deliveryHistories) { delivery ->
                            DeliveryHistoryCard(
                                delivery = delivery,
                                onClick = {
                                    navController.navigate("delivery-detail/${delivery.id}")
                                },
                            )
                        }
                    }
                }

                else -> {
                    Text(
                        text = "Data pengiriman tidak ditemukan",
                        modifier =
                            Modifier
                                .align(Alignment.Center)
                                .padding(16.dp),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
    }
}

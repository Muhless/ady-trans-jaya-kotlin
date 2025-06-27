package com.adytransjaya.ui.screen.delivery.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.adytransjaya.data.BackButton
import com.adytransjaya.data.repository.DeliveryRepository
import com.adytransjaya.ui.components.card.delivery.DeliveryDetailContent
import com.adytransjaya.ui.screen.delivery.DeliveryViewModel
import com.adytransjaya.ui.theme.AppColors

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryDetailScreen(
    deliveryId: Int,
    viewModel: DeliveryViewModel,
    repository: DeliveryRepository,
    navController: NavController,
) {
    val deliveryDetail by viewModel.getDeliveryById.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    LaunchedEffect(deliveryId) {
        viewModel.getDeliveryById(deliveryId)
    }

    Box(modifier = Modifier.background(AppColors.background)) {
        when {
            isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            error != null -> Text("Gagal: $error", modifier = Modifier.align(Alignment.Center))
            deliveryDetail != null ->
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                    ) {
                        BackButton(
                            onBackClick = { navController.popBackStack() },
                            modifier = Modifier.align(Alignment.CenterStart),
                        )

                        Text(
                            text = "Detail Pengiriman",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }

                    DeliveryDetailContent(
                        delivery = deliveryDetail!!,
                        navController = navController,
                        repository = repository,
                        viewModel = viewModel,
                    )
                }

            else -> Text("Data tidak ditemukan", modifier = Modifier.align(Alignment.Center))
        }
    }
}

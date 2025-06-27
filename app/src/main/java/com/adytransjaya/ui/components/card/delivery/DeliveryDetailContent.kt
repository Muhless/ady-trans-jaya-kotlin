package com.adytransjaya.ui.components.card.delivery

import ProgressInfoCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adytransjaya.data.model.DeliveryItem
import com.adytransjaya.data.repository.DeliveryRepository
import com.adytransjaya.ui.screen.delivery.DeliveryViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryDetailContent(
    delivery: DeliveryItem,
    repository: DeliveryRepository,
    viewModel: DeliveryViewModel,
    navController: NavController,
) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                DeliveryInfoCard(delivery = delivery)
            }
            item {
                ProgressInfoCard(
                    delivery = delivery,
                    repository = repository,
                    viewModel = viewModel,
                )
            }
            item {
                CustomerInfoCard(delivery = delivery)
            }
            item {
                ItemInfoCard(delivery = delivery)
            }
        }
    }
}

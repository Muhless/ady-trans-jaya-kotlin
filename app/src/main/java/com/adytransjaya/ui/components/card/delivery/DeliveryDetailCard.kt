package com.adytransjaya.ui.components.card.delivery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adytransjaya.data.model.DeliveryItem
import com.adytransjaya.ui.theme.AppColors

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryDetailCard(delivery: DeliveryItem) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth(),
    ) {
        Column(
            modifier =
                Modifier
                    .background(AppColors.background)
                    .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(text = "Detail Pengiriman", style = MaterialTheme.typography.titleLarge)

            CustomerInfoCard(delivery = delivery)
            DeliveryInfoCard(delivery = delivery)
            ItemInfoCard(delivery = delivery)
        }
    }
}

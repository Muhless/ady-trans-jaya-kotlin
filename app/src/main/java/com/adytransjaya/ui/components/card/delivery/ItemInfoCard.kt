package com.adytransjaya.ui.components.card.delivery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adytransjaya.data.model.DeliveryItem
import com.adytransjaya.ui.components.Divider
import com.adytransjaya.ui.theme.AppColors

@Suppress("ktlint:standard:function-naming")
@Composable
fun ItemInfoCard(delivery: DeliveryItem) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .background(AppColors.card)
                    .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = "Informasi Barang",
                style = MaterialTheme.typography.titleMedium,
            )
            Divider()
            DeliveryList(label = "Jenis Barang", value = delivery.loadType)
            DeliveryList(label = "Total Barang", value = delivery.totalItem)
            DeliveryList(label = "Total Berat", value = delivery.totalWeight)
            delivery.items.forEach { item ->
                DeliveryList(label = "Nama Barang", value = item.itemName)
                DeliveryList(label = "Jumlah Barang", value = item.quantity)
                DeliveryList(label = "Berat Barang", value = "${item.weight}")
            }
        }
    }
}

package com.adytransjaya.ui.components.card.delivery

import UnderlinedClickableText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.adytransjaya.utils.dateFormatter

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryInfoCard(delivery: DeliveryItem) {
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
                text = "Informasi Pengiriman",
                style = MaterialTheme.typography.titleMedium,
            )
            Divider()
            DeliveryList(label = "Kode Pengiriman", value = delivery.deliveryCode)
            DeliveryList(label = "Alamat Penjemputan", value = delivery.pickupAddress)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("")
                UnderlinedClickableText(
                    label = "lihat lokasi",
                    pickupLat = delivery.pickupAddressLat,
                    pickupLng = delivery.pickupAddressLang,
                )
            }
            DeliveryList(label = "Alamat Tujuan", value = delivery.destinationAddress)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("")
                UnderlinedClickableText(
                    label = "lihat lokasi",
                    deliveryLat = delivery.destinationAddressLat,
                    deliveryLng = delivery.destinationAddressLang,
                )
            }
            DeliveryList(label = "Tanggal Pengiriman", value = dateFormatter(delivery.deliveryDate))
            DeliveryList(
                label = "Batas Pengiriman",
                value = dateFormatter(delivery.deliveryDeadlineDate),
            )
            DeliveryList(label = "Status Pengiriman", value = delivery.deliveryStatus)
        }
    }
}

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adytransjaya.data.model.DeliveryItem

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryDetailCard(delivery: DeliveryItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier =
                Modifier
                    .background(Color.White)
                    .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(text = "Detail Pengiriman", style = MaterialTheme.typography.titleLarge)

            Card(
                modifier =
                    Modifier
                        .fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    DeliveryList(
                        label = "Nama Pelanggan",
                        value = delivery.transaction.customer.name,
                    )
                    DeliveryList(
                        label = "Perusahaan/Toko",
                        value = delivery.transaction.customer.company,
                    )
                    DeliveryList(
                        label = "Nomor Telepon",
                        value = delivery.transaction.customer.phone,
                    )
                }
            }

            Card(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(3.dp),
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    DeliveryList(label = "Kode Pengiriman", value = delivery.deliveryCode)
                    DeliveryList(label = "Jenis Barang", value = delivery.loadType)
                    DeliveryList(label = "Total Barang", value = delivery.totalItem)
                    DeliveryList(label = "Total Berat", value = delivery.totalWeight)
                    DeliveryList(label = "Alamat Penjemputan", value = delivery.pickupAddress)
                    DeliveryList(label = "Alamat Tujuan", value = delivery.destinationAddress)
                    DeliveryList(label = "Tanggal Pengiriman", value = delivery.deliveryDate)
                    DeliveryList(label = "Batas Pengiriman", value = delivery.deliveryDeadlineDate)
                }
            }
            DeliveryItemList(delivery = delivery)
        }
    }
}

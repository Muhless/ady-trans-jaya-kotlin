package com.adytransjaya.ui.components.card.delivery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DeliveryDetail(
    val transaction_status: String,
)

@Composable
fun deliveryDetailCard(detail: DeliveryDetail) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            Text(
                text = "Informasi Pengiriman",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2C3E50),
                modifier = Modifier.padding(bottom = 16.dp),
            )

//            infoRow(Icons.Default.Receipt, "Kode Invoice", detail.invoiceCode)
//            infoRow(Icons.Default.Person, "Pelanggan", detail.customerName)
//            infoRow(Icons.Default.Phone, "No. Telepon", detail.phone)
//            infoRow(Icons.Default.LocalShipping, "Kendaraan", detail.vehicle)
//            infoRow(Icons.Default.LocationOn, "Alamat Pengiriman", detail.deliveryAddress)
//            infoRow(Icons.Default.LocationOn, "Alamat Tujuan", detail.destinationAddress)
//            infoRow(Icons.Default.Schedule, "Estimasi", detail.estimatedTime)
            infoRow(Icons.Default.TaskAlt, "Status", detail.transaction_status)
        }
    }
}

@Composable
fun infoRow(
    icon: ImageVector,
    label: String,
    value: String,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF6C757D),
            modifier = Modifier.size(20.dp),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color(0xFF6C757D),
            )
            Text(
                text = value,
                fontSize = 14.sp,
                color = Color(0xFF2C3E50),
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

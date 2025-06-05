package com.adytransjaya.ui.components.card.delivery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Schedule
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

@Composable
fun deliveryDetailCard(modifier: Modifier = Modifier) {
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

            infoRow(
                icon = Icons.Default.Receipt,
                label = "Kode Invoice",
                value = "INV-20250604",
            )
            infoRow(
                icon = Icons.Default.Person,
                label = "Pelanggan",
                value = "Toko Berkah Jaya",
            )
            infoRow(
                icon = Icons.Default.Phone,
                label = "No. Telepon",
                value = "+62 812-3456-7890",
            )
            infoRow(
                icon = Icons.Default.LocalShipping,
                label = "Kendaraan",
                value = "Toyota Pickup",
            )
            infoRow(
                icon = Icons.Default.LocationOn,
                label = "Alamat Pengiriman",
                value = "Jl. Raya Bogor No. 123, Jakarta Timur",
            )
            infoRow(
                icon = Icons.Default.LocationOn,
                label = "Alamat Tujuan",
                value = "Jl. Raya Bogor No. 123, Jakarta Timur",
            )

            infoRow(
                icon = Icons.Default.Schedule,
                label = "Estimasi",
                value = "3 Juni 2025, 15:00 WIB",
            )
            infoRow(
                icon = Icons.Default.TaskAlt,
                label = "Status",
                value = "Selesai",
            )
        }
    }
}

@Composable
private fun infoRow(
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

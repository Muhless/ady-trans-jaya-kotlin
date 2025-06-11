package com.adytransjaya.ui.components.card.delivery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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

data class DeliveryCihuy(
    val transactionStatus: String,
)

@Composable
fun deliveryDetailCard(detail: DeliveryCihuy) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Invoice: ${detail.transactionStatus}")
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

package com.adytransjaya.ui.components.card.delivery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryList(
    label: String,
    value: Any,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

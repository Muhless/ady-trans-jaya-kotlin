package com.adytransjaya.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun Divider() {
    HorizontalDivider(
        color = Color.LightGray,
        thickness = 1.dp,
        modifier = Modifier.fillMaxWidth(),
    )
}

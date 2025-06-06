package com.adytransjaya.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun menuSelect(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.padding(20.dp)) {
        Text(
            text = "Menu",
            color = Color.Gray,
            fontSize = 18.sp,
            modifier = Modifier.padding(20.dp),
        )
        Row {
        }
    }
}

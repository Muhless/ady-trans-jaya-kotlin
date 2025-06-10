package com.adytransjaya.ui.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adytransjaya.ui.theme.AppColors
import com.adytransjaya.ui.theme.monsterratFontFamily

@Composable
fun titleCard(title: String) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(Color.White),
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = monsterratFontFamily,
            color = AppColors.NeutralText,
        )
    }
}

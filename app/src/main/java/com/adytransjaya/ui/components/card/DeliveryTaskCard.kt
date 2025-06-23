package com.adytransjaya.ui.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adytransjaya.R
import com.adytransjaya.ui.theme.AppColors

@Composable
fun deliveryTaskCard() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .height(170.dp)
                .clip(RoundedCornerShape(10.dp))
                .shadow(1.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = "delivery task background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize(),
            )
            Box(
                modifier =
                    Modifier
                        .matchParentSize(),
            )
            Text(
                modifier =
                    Modifier
                        .align(Alignment.Center)
                        .padding(10.dp),
                text = "Anda belum memiliki tugas pengiriman",
                fontSize = 18.sp,
                color = AppColors.background,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}

package adytransjaya.ui.components.card

import adytransjaya.ui.theme.AppColors
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun deliveryhHistoryCard(status: String) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 10.dp,
                ).background(Color.White)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape =
                        RoundedCornerShape(
                            5.dp,
                        ),
                ),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
        ) {
            Text(
                text = "Pengiriman 1",
                modifier = Modifier.weight(1f),
            )
            val statusColor =
                when (status.lowercase()) {
                    "selesai" -> AppColors.Success
                    "dibatalkan" -> Color.Red
                    else -> Color.Black
                }
            Text(
                text = status.replaceFirstChar { it.uppercase() },
                color = statusColor,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            )
        }
    }
}

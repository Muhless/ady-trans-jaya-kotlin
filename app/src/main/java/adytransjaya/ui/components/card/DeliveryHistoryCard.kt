package adytransjaya.ui.components.card

import adytransjaya.ui.theme.AppColors
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun deliveryhHistoryCard(
    status: String,
    navController: NavController,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable { navController.navigate("delivery_detail") },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "kode invoice",
                    fontSize = 17.sp,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text =
                        if (status.lowercase() == "dibatalkan") {
                            "Dibatalkan pada\n 3 Juni 2025, 14:30 WIB"
                        } else {
                            "Diselesaikan pada\n 3 Juni 2025, 14:30 WIB"
                        },
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
            }
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

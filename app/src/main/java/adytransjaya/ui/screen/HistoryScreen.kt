package adytransjaya.ui.screen

import adytransjaya.ui.components.card.deliveryhHistoryCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun historyScreen(navController: NavController) {
    Column {
        Text(
            text = "Riwayat Pengiriman",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 30.dp,
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp,
                    ),
        )
        deliveryhHistoryCard(status = "selesai", navController)
        deliveryhHistoryCard(status = "dibatalkan", navController)
        deliveryhHistoryCard(status = "selesai", navController)
        deliveryhHistoryCard(status = "pending", navController)
    }
}

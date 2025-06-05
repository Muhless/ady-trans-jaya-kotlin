package adytransjaya.ui.screen

import adytransjaya.ui.components.card.deliveryhHistoryCard
import adytransjaya.ui.components.card.titleCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun historyScreen(navController: NavController) {
    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item { titleCard(title = "Riwayat Pengiriman") }
        item {
            deliveryhHistoryCard(status = "selesai", navController)
            deliveryhHistoryCard(status = "dibatalkan", navController)
            deliveryhHistoryCard(status = "selesai", navController)
            deliveryhHistoryCard(status = "pending", navController)
        }
    }
}

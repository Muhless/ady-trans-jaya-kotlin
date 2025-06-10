package com.adytransjaya.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adytransjaya.ui.components.card.titleCard

@Composable
fun historyScreen(navController: NavController) {
    titleCard(title = "Riwayat Pengiriman")
    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
//        items(deliveries) { status ->
//            deliveryhHistoryCard(status = status, navController)
//        }
    }
}

package com.adytransjaya.ui.screen.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adytransjaya.ui.components.card.delivery.deliveryDetailCard
import com.adytransjaya.ui.components.card.titleCard

@Composable
fun detailHistoryScreen() {
    Column {
        titleCard(title = "Detail Riwayat Pengiriman")
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item { deliveryDetailCard() }
        }
    }
}

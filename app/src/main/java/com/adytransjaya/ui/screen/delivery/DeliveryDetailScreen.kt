package com.adytransjaya.ui.screen.delivery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adytransjaya.ui.components.card.delivery.deliveryDetailCard
import com.adytransjaya.ui.components.card.titleCard
import com.adytransjaya.ui.theme.AppColors

data class DeliveryItem(
    val nama: String,
    val jumlah: Int,
    val satuan: String,
    val berat: Double,
)

data class TrackingStep(
    val waktu: String,
    val status: String,
    val lokasi: String,
    val keterangan: String,
    val isCompleted: Boolean,
)

@Composable
fun deliveryDetailScreen(navController: NavController) {
    val deliveryItems =
        listOf(
            DeliveryItem("Beras Premium", 50, "Karung", 25.0),
            DeliveryItem("Gula Pasir", 20, "Karung", 10.0),
            DeliveryItem("Minyak Goreng", 12, "Jerigen", 6.0),
            DeliveryItem("Tepung Terigu", 15, "Karung", 7.5),
        )

    val trackingSteps =
        listOf(
            TrackingStep(
                "3 Juni 2025, 08:00",
                "Pesanan Dikonfirmasi",
                "Gudang Pusat",
                "Pesanan telah dikonfirmasi dan siap diproses",
                true,
            ),
            TrackingStep(
                "3 Juni 2025, 10:30",
                "Barang Dikemas",
                "Gudang Pusat",
                "Barang sedang dikemas untuk pengiriman",
                true,
            ),
            TrackingStep(
                "3 Juni 2025, 12:00",
                "Dalam Perjalanan",
                "Jakarta Pusat",
                "Barang dalam perjalanan menuju tujuan",
                true,
            ),
            TrackingStep(
                "3 Juni 2025, 14:30",
                "Terkirim",
                "Toko Berkah Jaya",
                "Barang telah sampai dan diterima customer",
                true,
            ),
        )

    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            titleCard(title = "Detail Pengiriman")
        }

        item {
            deliveryDetailCard()
        }

        // Daftar Barang
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Daftar Barang",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF2C3E50),
                        )

                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F4FD)),
                            shape = RoundedCornerShape(12.dp),
                        ) {
                            Text(
                                text = "${deliveryItems.size} Item",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontSize = 12.sp,
                                color = Color(0xFF1976D2),
                                fontWeight = FontWeight.Medium,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    deliveryItems.forEach { item ->
                        itemRow(item = item)
                        if (item != deliveryItems.last()) {
                            Divider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                color = Color(0xFFE0E0E0),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .background(
                                    Color(0xFFF8F9FA),
                                    RoundedCornerShape(8.dp),
                                )
                                .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "Total Berat:",
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF2C3E50),
                        )
                        Text(
                            text = "${deliveryItems.sumOf { it.berat }} Kg",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1976D2),
                        )
                    }
                }
            }
        }

        item {
            Button(
                onClick = { navController.popBackStack() },
                modifier =
                    Modifier
                        .fillMaxWidth(),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = AppColors.BrandBlue,
                        contentColor = Color.White,
                    ),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Kembali")
            }
        }
    }
}

@Composable
private fun itemRow(item: DeliveryItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.nama,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF2C3E50),
            )
            Text(
                text = "${item.berat} Kg",
                fontSize = 12.sp,
                color = Color(0xFF6C757D),
            )
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FA)),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(
                text = "${item.jumlah} ${item.satuan}",
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                fontSize = 12.sp,
                color = Color(0xFF495057),
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
private fun trackingStepItem(
    step: TrackingStep,
    isLast: Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier.size(24.dp),
                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            if (step.isCompleted) {
                                AppColors.Success
                            } else {
                                Color(
                                    0xFFE0E0E0,
                                )
                            },
                    ),
                shape = RoundedCornerShape(12.dp),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = if (step.isCompleted) Icons.Default.Check else Icons.Default.Circle,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(12.dp),
                    )
                }
            }

            if (!isLast) {
                Box(
                    modifier =
                        Modifier
                            .width(2.dp)
                            .height(60.dp)
                            .background(
                                if (step.isCompleted) {
                                    AppColors.Success.copy(alpha = 0.3f)
                                } else {
                                    Color(
                                        0xFFE0E0E0,
                                    )
                                },
                            ),
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier =
                Modifier
                    .weight(1f)
                    .padding(bottom = if (!isLast) 16.dp else 0.dp),
        ) {
            Text(
                text = step.status,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2C3E50),
            )
            Text(
                text = step.waktu,
                fontSize = 12.sp,
                color = Color(0xFF6C757D),
            )
            Text(
                text = step.lokasi,
                fontSize = 12.sp,
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Medium,
            )
            if (step.keterangan.isNotEmpty()) {
                Text(
                    text = step.keterangan,
                    fontSize = 12.sp,
                    color = Color(0xFF6C757D),
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
        }
    }
}

package com.adytransjaya.ui.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adytransjaya.R
import com.adytransjaya.data.model.DeliveryItem
import com.adytransjaya.ui.components.Divider
import com.adytransjaya.ui.components.card.delivery.DeliveryList
import com.adytransjaya.ui.theme.AppColors
import com.adytransjaya.ui.theme.monsterratFontFamily
import com.adytransjaya.utils.dateFormatter

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryTaskCard(
    activeDelivery: DeliveryItem? = null,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        when {
            isLoading -> LoadingState()
            errorMessage != null -> ErrorState(errorMessage)
            activeDelivery != null -> ActiveDeliveryContent(activeDelivery, navController)
            else -> EmptyState()
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun LoadingState() {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(AppColors.BrandBlueLight),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(40.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Memuat tugas pengiriman...",
                color = Color.White,
                fontFamily = monsterratFontFamily,
                fontSize = 16.sp,
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun ErrorState(errorMessage: String) {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color.Red.copy(alpha = 0.1f)),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = errorMessage,
            color = Color.Red,
            fontFamily = monsterratFontFamily,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(24.dp),
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun EmptyState() {
    Box(
        modifier =
            Modifier
                .fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_delivery4),
            contentDescription = "delivery task background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(
                        brush =
                            Brush.verticalGradient(
                                colors =
                                    listOf(
                                        Color.Black.copy(alpha = 0.1f),
                                        Color.Black.copy(alpha = 0.3f),
                                    ),
                            ),
                    ),
        )

        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(24.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Anda belum memiliki tugas pengiriman",
                fontSize = 18.sp,
                fontFamily = monsterratFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun ActiveDeliveryContent(
    delivery: DeliveryItem,
    navController: NavController,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Tugas Pengiriman Aktif",
                fontSize = 18.sp,
                fontFamily = monsterratFontFamily,
                fontWeight = FontWeight.Bold,
                color = AppColors.BrandBlueDark,
                modifier = Modifier.align(Alignment.Center),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Divider()

        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier =
                Modifier
                    .background(AppColors.card)
                    .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            DeliveryList(label = "Kode Pengiriman", value = delivery.deliveryCode)
            DeliveryList(label = "Alamat Penjemputan", value = delivery.pickupAddress)
            DeliveryList(label = "Alamat Tujuan", value = delivery.destinationAddress)
            DeliveryList(label = "Tanggal Pengiriman", value = dateFormatter(delivery.deliveryDate))
            DeliveryList(
                label = "Batas Pengiriman",
                value = dateFormatter(delivery.deliveryDeadlineDate),
            )
            DeliveryList(label = "Status Pengiriman", value = delivery.deliveryStatus)
        }
        Button(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
            onClick = {
                navController.navigate("delivery/${delivery.id}")
            },
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = AppColors.BrandBlue,
                    contentColor = AppColors.base,
                ),
        ) {
            Text("Lihat Selengkapnya")
        }
    }
}

package com.adytransjaya.ui.screen.delivery

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adytransjaya.ui.components.card.delivery.DeliveryDetail
import com.adytransjaya.ui.components.card.delivery.deliveryDetailCard
import com.adytransjaya.ui.components.card.delivery.deliveryOnProcessCard
import com.adytransjaya.ui.components.card.titleCard
import com.adytransjaya.ui.screen.login.LoginViewModel
import com.adytransjaya.ui.theme.AppColors

@Composable
fun deliveryScreen(
    navController: NavController,
    deliveryStatus: String = "menunggu persetujuan",
    destinationLat: Double = 0.0,
    destinationLng: Double = 0.0,
    loginViewModel: LoginViewModel,
) {
    val deliveries by loginViewModel.deliveries.value
    val context = LocalContext.current

    Column(modifier = Modifier) {
        titleCard(title = "Pengiriman")
        LazyColumn(
            modifier =
                Modifier
                    .padding(20.dp),
            verticalArrangement =
                Arrangement.spacedBy(
                    16.dp,
                ),
        ) {
            items(deliveries) { delivery ->
                deliveryDetailCard(
                    detail =
                        DeliveryDetail(
//                            invoiceCode = delivery.transaction.payment_deadline,
//                            customerName = delivery.transaction.down_payment,
//                            phone = delivery.transaction.down_payment_status,
//                            vehicle = delivery.transaction.down_payment_time,
//                            deliveryAddress = delivery.transaction.full_payment,
//                            destinationAddress = delivery.transaction.full_payment_status,
//                            estimatedTime = delivery.transaction.full_payment_time,
                            status = delivery.transaction?.transaction_status ?: "-",
                        ),
                )
            }
            item {
                if (deliveryStatus != "dalam pengiriman") {
                    Button(
                        onClick = {
                            val gmmIntentUri =
                                Uri.parse("google.navigation:q=$destinationLat,$destinationLng")
                            val mapIntent =
                                Intent(Intent.ACTION_VIEW, gmmIntentUri)
                                    .apply { setPackage("com.google.android.apps.maps") }

                            if (mapIntent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(mapIntent)
                            } else {
                                Toast
                                    .makeText(
                                        context,
                                        "Google Maps tidak ditemukan, membuka di browser...",
                                        Toast.LENGTH_SHORT,
                                    ).show()

                                val browserIntent =
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://www.google.com/maps/dir/?api=1&destination=$destinationLat,$destinationLng"),
                                    )
                                context.startActivity(browserIntent)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = AppColors.BrandBlue,
                                contentColor = Color.White,
                            ),
                    ) {
                        Text(text = "Mulai Pengiriman")
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                } else {
                    deliveryOnProcessCard()
                }
            }
        }
    }
}

package adytransjaya.ui.screen.delivery

import adytransjaya.ui.components.card.delivery.deliveryDetailCard
import adytransjaya.ui.components.card.delivery.deliveryOnProcessCard
import adytransjaya.ui.components.card.titleCard
import adytransjaya.ui.theme.AppColors
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun deliveryScreen(
    navController: NavController,
    deliveryStatus: String = "menunggu persetujuan",
    destinationLat: Double = 0.0,
    destinationLng: Double = 0.0,
) {
    val context = LocalContext.current
    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(20.dp),
        verticalArrangement =
            Arrangement.spacedBy(
                16.dp,
            ),
    ) {
        item {
            titleCard(title = "Pengiriman")
        }

        item { deliveryDetailCard() }
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

package com.adytransjaya.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.adytransjaya.R
import com.adytransjaya.ui.components.card.deliveryTaskCard
import com.adytransjaya.ui.components.card.menuCard
import com.adytransjaya.ui.screen.login.LoginViewModel
import com.adytransjaya.ui.theme.AppColors
import com.adytransjaya.ui.theme.monsterratFontFamily

@Composable
fun homeScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
) {
    Log.d("HomeScreen", "Using ViewModel instance: ${loginViewModel.hashCode()}")
    val driver by loginViewModel.driver

    LaunchedEffect(driver) {
        Log.d("HomeScreen", "Driver in HomeScreen: $driver")
    }

    Column {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        brush =
                            Brush.verticalGradient(
                                colors =
                                    listOf(
                                        AppColors.BrandBlue,
                                        AppColors.BrandBlue.copy(alpha = 0.8f),
                                    ),
                            ),
                    ),
        ) {
            Row(
                modifier =
                    Modifier
                        .padding(
                            start = 30.dp,
                            end = 30.dp,
                            top = 30.dp,
                        ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier =
                        Modifier
                            .weight(1f),
                ) {
                    Text(
                        text = "Halo,",
                        fontSize = 18.sp,
                        color = Color.LightGray,
                    )
                    Text(
                        text = driver?.name ?: "Driver belum tersedia",
                        fontSize = 25.sp,
                        color = Color.White,
                        fontFamily = monsterratFontFamily,
                    )
                }
                AsyncImage(
                    model = driver?.imageUrl ?: R.drawable.profile_picture,
                    contentDescription = if (driver?.photo != null) "Profile picture" else "Default Profile picture",
                    onError = {
                        Log.e("HomeScreen", "Failed to load image: ${it.result.throwable}")
                    },
                    modifier =
                        Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.profile_picture),
                    error = painterResource(id = R.drawable.profile_picture),
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        deliveryTaskCard()

        Spacer(modifier = Modifier.height(30.dp))

        Column(modifier = Modifier.padding(horizontal = 30.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                menuCard(
                    image = R.drawable.icon_location,
                    title = "Pengiriman",
                    onClick = { navController.navigate("delivery") },
                    modifier = Modifier.weight(1f),
                )
                Spacer(modifier = Modifier.width(15.dp))
                menuCard(
                    image = R.drawable.icon_search,
                    title = "Riwayat",
                    onClick = { navController.navigate("history") },
                    modifier = Modifier.weight(1f),
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
// MENU
            Row(modifier = Modifier.fillMaxWidth()) {
                menuCard(
                    image = R.drawable.icon_profile,
                    title = "Profil",
                    onClick = { navController.navigate("profile") },
                    modifier = Modifier.weight(1f),
                )
                Spacer(modifier = Modifier.width(15.dp))
                menuCard(
                    image = R.drawable.icon_chat,
                    title = "Bantuan",
                    onClick = { navController.navigate("help") },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

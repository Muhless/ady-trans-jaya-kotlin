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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.adytransjaya.ui.components.card.DeliveryTaskCard
import com.adytransjaya.ui.components.card.MenuList
import com.adytransjaya.ui.screen.delivery.DeliveryViewModel
import com.adytransjaya.ui.screen.login.LoginViewModel
import com.adytransjaya.ui.theme.AppColors
import com.adytransjaya.ui.theme.monsterratFontFamily

@Composable
fun homeScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
    deliveryViewModel: DeliveryViewModel,
) {
    val driver by loginViewModel.driver
    val delivery by deliveryViewModel.activeDelivery.collectAsState()
    val isLoading by deliveryViewModel.isLoading.collectAsState()
    val errorMessage by deliveryViewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        deliveryViewModel.getActiveDelivery()
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
                            start = 40.dp,
                            end = 40.dp,
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

        Spacer(modifier = Modifier.height(20.dp))

        MenuList(navController = navController)

        DeliveryTaskCard(
            activeDelivery = delivery,
            isLoading = isLoading,
            errorMessage = errorMessage,
            navController = navController,
        )
    }
}

package com.adytransjaya.ui.screen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adytransjaya.R
import com.adytransjaya.ui.components.card.deliveryTaskCard
import com.adytransjaya.ui.components.card.menuCard
import com.adytransjaya.ui.screen.login.LoginViewModel
import com.adytransjaya.ui.screen.profile.ProfileViewModel
import com.adytransjaya.ui.theme.AppColors

@Composable
fun homeScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
    profileViewModel: ProfileViewModel,
) {
    val driverId = loginViewModel.driverId
    val username = loginViewModel.username // Ambil username dari loginViewModel
    val profile = profileViewModel.profile
    val isProfileLoading = profileViewModel.isLoading

    LaunchedEffect(driverId) {
        if (driverId != 0) {
            profileViewModel.loadProfileById(driverId)
        }
    }

    LaunchedEffect(Unit) {
        loginViewModel.loadSavedData()
    }

    Column {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
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
                    color = Color.Gray,
                )

                when {
                    isProfileLoading -> {
                        // Tampilkan loading dengan shimmer effect
                        Box(
                            modifier =
                                Modifier
                                    .height(40.dp)
                                    .width(200.dp)
                                    .background(
                                        Color.Gray.copy(alpha = 0.3f),
                                        RoundedCornerShape(4.dp),
                                    ),
                        )
                    }

                    profile != null -> {
                        // Tampilkan nama dari profile jika tersedia
                        Text(
                            text = profile.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 36.sp,
                            color = AppColors.BrandBlueDark,
                        )
                    }

                    username.isNotEmpty() -> {
                        // Fallback ke username jika profile belum load
                        Text(
                            text = username,
                            fontWeight = FontWeight.Bold,
                            fontSize = 36.sp,
                            color = AppColors.BrandBlueDark,
                        )
                    }

                    else -> {
                        // Fallback terakhir
                        Text(
                            text = "User",
                            fontWeight = FontWeight.Bold,
                            fontSize = 36.sp,
                            color = AppColors.BrandBlueDark,
                        )
                    }
                }
            }

            // Profile Picture dengan handling berbagai state
            Box(
                modifier =
                    Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.Gray.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center,
            ) {
                when {
                    isProfileLoading -> {
                        // Loading state untuk gambar
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp,
                            color = AppColors.BrandBlue,
                        )
                    }

                    else -> {
                        AsyncImage(
                            model =
                                ImageRequest
                                    .Builder(LocalContext.current)
                                    .data(
                                        if (!profile?.photo.isNullOrBlank()) {
                                            "http://192.168.3.229:8080${profile?.photo}"
                                        } else {
                                            null
                                        },
                                    ).crossfade(true)
                                    .placeholder(R.drawable.profile_picture)
                                    .fallback(R.drawable.profile_picture)
                                    .error(R.drawable.profile_picture)
                                    .build(),
                            contentDescription = "Profile Picture for ${profile?.name ?: username}",
                            contentScale = ContentScale.Crop,
                            modifier =
                                Modifier
                                    .size(48.dp)
                                    .clip(CircleShape),
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        deliveryTaskCard()

        Spacer(modifier = Modifier.height(30.dp))

        Column(modifier = Modifier.padding(horizontal = 30.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                menuCard(
                    icon = Icons.Default.LocalShipping,
                    title = "Pengiriman",
                    onClick = { navController.navigate("delivery") },
                    modifier = Modifier.weight(1f),
                )
                Spacer(modifier = Modifier.width(15.dp))
                menuCard(
                    icon = Icons.Default.History,
                    title = "Riwayat",
                    onClick = { navController.navigate("history") },
                    modifier = Modifier.weight(1f),
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
// MENU
            Row(modifier = Modifier.fillMaxWidth()) {
                menuCard(
                    icon = Icons.Default.Person,
                    title = "Profile",
                    onClick = { navController.navigate("profile") },
                    modifier = Modifier.weight(1f),
                )
                Spacer(modifier = Modifier.width(15.dp))
                menuCard(
                    icon = Icons.AutoMirrored.Filled.Help,
                    title = "Bantuan",
                    onClick = { navController.navigate("help") },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

package com.adytransjaya.ui.screen.profile

import ConfirmationDialog
import android.app.Activity
import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.adytransjaya.R
import com.adytransjaya.ui.components.profile.ProfilInfoCard
import com.adytransjaya.ui.components.profile.settingButton
import com.adytransjaya.ui.screen.login.LoginViewModel
import com.adytransjaya.ui.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun profileScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
) {
    val driver by loginViewModel.driver
    val context = LocalContext.current
    val activity = context as? Activity
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(driver) {
        Log.d("ProfileScreen", "Driver in ProfileScreen: $driver")
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(280.dp)
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
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Card(
                    modifier = Modifier.size(140.dp),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.cardElevation(8.dp),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        AsyncImage(
                            model = driver?.imageUrl ?: R.drawable.profile_picture,
                            contentDescription = "Profile picture",
                            onError = {
                                Log.e(
                                    "ProfileScreen",
                                    "Failed to load image: ${it.result.throwable}",
                                )
                            },
                            modifier =
                                Modifier
                                    .size(130.dp)
                                    .clip(CircleShape),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(id = R.drawable.profile_picture),
                            error = painterResource(id = R.drawable.profile_picture),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    colors = CardDefaults.cardColors(Color.White.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(20.dp),
                ) {
                    Text(
                        text = driver?.name ?: "Nama tidak tersedia",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }

        ProfilInfoCard(driver!!)

        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
            ) {
                Text(
                    text = "Pengaturan Akun",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.BrandBlueDark,
                    modifier = Modifier.padding(bottom = 16.dp),
                )
                com.adytransjaya.ui.components
                    .Divider()

                settingButton(
                    icon = Icons.Default.Logout,
                    text = "Keluar",
                    onClick = {
                        showDialog = true
                    },
                )
            }
        }
        if (showDialog) {
            val context = LocalContext.current
            val activity = context as? Activity

            ConfirmationDialog(
                showDialog = showDialog,
                onDismiss = { showDialog = false },
                onConfirm = {
                    showDialog = false
                    activity?.let {
                        ActivityCompat.finishAffinity(it)
                    }
                },
                title = "Konfirmasi",
                message = "Apakah Anda yakin ingin keluar dari aplikasi ?",
            )
        }
    }
}

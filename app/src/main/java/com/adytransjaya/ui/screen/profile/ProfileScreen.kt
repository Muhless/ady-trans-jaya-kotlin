package com.adytransjaya.ui.screen.profile

import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.adytransjaya.R
import com.adytransjaya.ui.screen.login.LoginViewModel
import com.adytransjaya.ui.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun profileScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
) {
    val driver by loginViewModel.driver
    val imageUrl = driver?.photo?.takeIf { it.isNotEmpty() }?.let { "http://192.168.3.229:8080$it" }
    var showEditDialog by remember { mutableStateOf(false) }

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
                                        AppColors.BrandBlueDark,
                                        AppColors.BrandBlueDark.copy(alpha = 0.8f),
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
                // Profile Image with border
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
                            model = imageUrl,
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

                // Driver Name
                Text(
                    text = driver?.name ?: "Nama tidak tersedia",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )

                // Driver Status/Role
                Card(
                    colors = CardDefaults.cardColors(Color.White.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(20.dp),
                ) {
                    Text(
                        text = "Driver Profesional",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }

        // Profile Details Card
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
            ) {
                Text(
                    text = "Informasi Personal",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.BrandBlueDark,
                    modifier = Modifier.padding(bottom = 16.dp),
                )

                profileInfoItem(
                    icon = Icons.Default.Person,
                    label = "Nama Lengkap",
                    value = driver?.name ?: "Tidak tersedia",
                )

                profileInfoItem(
                    icon = Icons.Default.Phone,
                    label = "Nomor Telepon",
                    value = driver?.phone ?: "Tidak tersedia",
                )

                profileInfoItem(
                    icon = Icons.Default.LocationOn,
                    label = "Alamat",
                    value = driver?.address ?: "Tidak tersedia",
                )

                profileInfoItem(
                    icon = Icons.Default.AccountCircle,
                    label = "Username",
                    value = driver?.username ?: "Tidak tersedia",
                )
            }
        }

        // Action Buttons
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

                actionButton(
                    icon = Icons.Default.Edit,
                    text = "Edit Profile",
                    onClick = { showEditDialog = true },
                )

                actionButton(
                    icon = Icons.Default.Lock,
                    text = "Ubah Password",
                    onClick = { /* Navigate to change password */ },
                )

                actionButton(
                    icon = Icons.Default.Settings,
                    text = "Pengaturan",
                    onClick = { /* Navigate to settings */ },
                )

                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Gray.copy(alpha = 0.3f),
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }

    // Edit Profile Dialog
    if (showEditDialog) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = {
                Text(
                    text = "Edit Profile",
                    fontWeight = FontWeight.Bold,
                )
            },
            text = {
                Text("Fitur edit profile akan segera tersedia.")
            },
            confirmButton = {
                TextButton(
                    onClick = { showEditDialog = false },
                ) {
                    Text("OK")
                }
            },
        )
    }
}

@Composable
fun profileInfoItem(
    icon: ImageVector,
    label: String,
    value: String,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AppColors.BrandBlueDark,
            modifier =
                Modifier
                    .size(24.dp)
                    .padding(top = 2.dp),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = label,
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = value,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 2.dp),
            )
        }
    }
}

@Composable
fun actionButton(
    icon: ImageVector,
    text: String,
    textColor: Color = Color.Black,
    onClick: () -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
        onClick = onClick,
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (textColor == Color.Red) Color.Red else AppColors.BrandBlueDark,
                modifier = Modifier.size(24.dp),
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = text,
                fontSize = 16.sp,
                color = textColor,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f),
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(20.dp),
            )
        }
    }
}

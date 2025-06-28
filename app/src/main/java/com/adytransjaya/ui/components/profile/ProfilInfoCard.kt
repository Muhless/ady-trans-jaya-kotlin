package com.adytransjaya.ui.components.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adytransjaya.data.model.Driver
import com.adytransjaya.ui.components.Divider
import com.adytransjaya.ui.theme.AppColors

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProfilInfoCard(driver: Driver) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
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
            Divider()
            profileCard(
                icon = Icons.Default.AccountCircle,
                label = "Username",
                value = driver?.username ?: "Tidak tersedia",
            )
            profileCard(
                icon = Icons.Default.Person,
                label = "Nama Lengkap",
                value = driver?.name ?: "Tidak tersedia",
            )
            profileCard(
                icon = Icons.Default.Phone,
                label = "Nomor Telepon",
                value = driver?.phone ?: "Tidak tersedia",
            )
            profileCard(
                icon = Icons.Default.LocationOn,
                label = "Alamat",
                value = driver?.address ?: "Tidak tersedia",
            )
        }
    }
}

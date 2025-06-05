package com.adytransjaya.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adytransjaya.R
import com.adytransjaya.ui.components.card.deliveryTaskCard
import com.adytransjaya.ui.components.card.menuCard

@Composable
fun homeScreen(navController: NavController) {
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
                Text(
                    text = "Driver",
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    fontFamily = FontFamily.Cursive,
                    color = Color(0xFF1976D2),
                )
            }
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Profile Picture",
                modifier =
                    Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.LightGray, CircleShape),
                contentScale = ContentScale.Crop,
            )
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

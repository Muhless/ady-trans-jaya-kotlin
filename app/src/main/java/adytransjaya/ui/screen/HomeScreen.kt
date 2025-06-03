package adytransjaya.ui.screen

import ady_trans_jaya.kotlin.R
import adytransjaya.ui.components.menuCard
import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("ComposableNaming")
@Composable
fun homeScreen(navController: NavController) {
    Column(modifier = Modifier.padding(30.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Halo,", fontSize = 20.sp)
                Text(
                    text = "Driver",
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    fontFamily = FontFamily.Cursive,
                )
            }
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.Fit,
            )
        }
// TOP
        Spacer(modifier = Modifier.weight(1f))

        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                menuCard(
                    icon = Icons.Default.LocalShipping,
                    title = "Pengiriman",
                    subtitle = "Tugas pengiriman hari",
                    onClick = { navController.navigate("delivery") },
                    modifier = Modifier.weight(1f),
                )
                Spacer(modifier = Modifier.width(15.dp))
                menuCard(
                    icon = Icons.Default.History,
                    title = "Riwayat",
                    subtitle = "Riwayat pengiriman",
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
                    subtitle = "Informasi driver",
                    onClick = { navController.navigate("profile") },
                    modifier = Modifier.weight(1f),
                )
                Spacer(modifier = Modifier.width(15.dp))
                menuCard(
                    icon = Icons.Default.HelpOutline,
                    title = "Bantuan",
                    subtitle = "Bantuan",
                    onClick = { navController.navigate("help") },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

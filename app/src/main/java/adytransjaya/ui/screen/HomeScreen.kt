package adytransjaya.ui.screen

import ady_trans_jaya.kotlin.R
import adytransjaya.ui.components.menuBox
import adytransjaya.ui.components.recentDelivery
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("ComposableNaming")
@Composable
fun homeScreen(navController: NavController) {
    Column(modifier = Modifier.padding(20.dp)) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Ady Trans Jaya",
            modifier = Modifier.size(72.dp),
            contentScale = ContentScale.Fit,
        )
        Row(
            modifier =
                Modifier
                    .fillMaxWidth(),
        ) {
            Column {
                Text(text = "Halo,", fontSize = 25.sp)
                Text(
                    text = "Driver",
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    fontFamily = FontFamily.Cursive,
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

//        Menu
        Column {
            Row {
                menuBox(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(10.dp))
                menuBox(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row {
                menuBox(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(10.dp))
                menuBox(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.padding(20.dp))
//        Task
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Riwayat",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = "lihat selengkapnya",
                    color = Color(0xFF2196F3),
                    style = TextStyle(fontSize = 15.sp, textDecoration = TextDecoration.Underline),
                    modifier = Modifier.clickable { navController.navigate("history") },
                )
//                delivery
            }
            Spacer(modifier = Modifier.height(20.dp))
            recentDelivery()
        }
    }
}

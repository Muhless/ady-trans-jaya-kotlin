package adytransjaya.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@SuppressLint("ComposableNaming")
@Composable
fun deliveryScreen(navController: NavController) {
    Column { Text(text = "Delivery") }
}

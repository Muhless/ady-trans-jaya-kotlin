package adytransjaya.ui.screen.delivery

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("ComposableNaming")
@Composable
fun deliveryScreen(navController: NavController) {
    Column {
        Text(
            text = "Pengiriman",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier =
                Modifier
                    .padding(
                        top = 30.dp,
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp,
                    ),
        )
    }
}

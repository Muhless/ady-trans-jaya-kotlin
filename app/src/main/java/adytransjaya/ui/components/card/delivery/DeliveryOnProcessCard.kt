package adytransjaya.ui.components.card.delivery

import adytransjaya.ui.theme.AppColors
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun deliveryOnProcessCard() {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(100.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = AppColors.BrandBlueLight,
                contentColor = Color.White,
            ),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "Mulai Pengiriman :",
                fontSize = 15.sp,
            )
            Text(
                text = "16 Juni 2025",
                fontSize = 15.sp,
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = {},
        colors =
            ButtonDefaults.buttonColors(
                containerColor = AppColors.Success,
                contentColor = Color.White,
            ),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = "Selesaikan Pengiriman")
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
        )
    }
}

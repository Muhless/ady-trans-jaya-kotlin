package adytransjaya.ui.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun deliveryCard() {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(Color.White)
                .border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape =
                        RoundedCornerShape(
                            10.dp,
                        ),
                ),
    ) {
        Text(
            text = "Pengiriman 1",
            modifier =
                Modifier
                    .padding(20.dp),
        )
    }
}

package adytransjaya.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun menuBox(modifier: Modifier = Modifier) {
    Box(
        modifier =
            modifier
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White),
    ) {
        Text(text = "Ini box")
    }
}

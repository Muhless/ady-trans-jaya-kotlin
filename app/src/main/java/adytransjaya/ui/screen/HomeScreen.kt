package adytransjaya.ui.screen
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("ComposableNaming")
@Composable
fun homeScreen(navController: NavController) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .weight(1f)
                    .padding(vertical = 40.dp, horizontal = 32.dp),
        ) {
            Text(text = "Halo Driver,", fontSize = 25.sp)
        }
        Column(
            modifier =
                Modifier
                    .weight(1f)
                    .padding(vertical = 40.dp, horizontal = 32.dp),
        ) {
            Text(text = "Halo Driver,", fontSize = 30.sp)
        }
    }
}

package adytransjaya.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)

    object Delivery : BottomNavItem("delivery", "Pengiriman", Icons.AutoMirrored.Filled.Send)

    object History : BottomNavItem("history", "Riwayat", Icons.Default.DateRange)

    object Profile : BottomNavItem("detail", "Detail", Icons.Default.Person)
}

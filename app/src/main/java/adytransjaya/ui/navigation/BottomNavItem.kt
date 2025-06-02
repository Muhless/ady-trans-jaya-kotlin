package adytransjaya.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)

    object Profile : BottomNavItem("detail", "Detail", Icons.Default.Person)

    object History : BottomNavItem("history", "Riwayat", Icons.Default.DateRange)

    object Delivery : BottomNavItem("delivery", "Pengiriman", Icons.Default.LocationOn)
}

package com.adytransjaya.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    object Home : BottomNavItem("home", "Beranda", Icons.Default.Home)

    object Delivery : BottomNavItem("delivery", "Pengiriman", Icons.Default.LocalShipping)

    object History : BottomNavItem("history", "Riwayat", Icons.Default.History)

    object Profile : BottomNavItem("profile", "Profil", Icons.Default.Person)

    object LogOut : BottomNavItem("logout", "Keluar", Icons.Default.Logout)
}

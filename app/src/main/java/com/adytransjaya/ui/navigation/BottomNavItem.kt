package com.adytransjaya.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    object Home : BottomNavItem("home", "Beranda", Icons.Default.Home)

    object Delivery : BottomNavItem("delivery/{id}", "Pengiriman", Icons.Default.LocalShipping)

    object History : BottomNavItem("delivery-history", "Riwayat", Icons.Default.History)

    object Profile : BottomNavItem("profile", "Profil", Icons.Default.Person)

    object Help : BottomNavItem("help", "Bantuan", Icons.Default.Help)
}

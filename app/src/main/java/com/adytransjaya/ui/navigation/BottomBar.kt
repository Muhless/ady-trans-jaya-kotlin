package com.adytransjaya.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.adytransjaya.ui.theme.AppColors

@Composable
fun bottomBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val items =
        listOf(
            BottomNavItem.Home,
            BottomNavItem.Delivery,
            BottomNavItem.History,
            BottomNavItem.Profile,
            BottomNavItem.LogOut,
        )

    NavigationBar(
        modifier =
            Modifier
                .padding(vertical = 4.dp)
                .height(50.dp)
                .background(Color.White),
        containerColor = Color.Transparent,
        tonalElevation = 4.dp,
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) navController.navigate(item.route)
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            modifier = Modifier.size(20.dp),
                            tint = if (selected) AppColors.BrandBlue else Color(0xFF9CA3AF),
                        )
                        Text(
                            text = item.label,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            color = if (selected) AppColors.BrandBlue else Color(0xFF9CA3AF),
                        )
                    }
                },
                colors =
                    NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                    ),
            )
        }
    }
}

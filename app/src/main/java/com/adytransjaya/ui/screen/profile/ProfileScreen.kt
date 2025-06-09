package com.adytransjaya.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.adytransjaya.ui.components.card.titleCard

@Composable
fun profileScreen(navController: NavController) {
    Column {
        titleCard(title = "Profil")
    }
}

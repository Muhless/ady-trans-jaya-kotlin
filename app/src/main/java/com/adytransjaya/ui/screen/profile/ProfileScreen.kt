package com.adytransjaya.ui.screen.profile

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.adytransjaya.ui.components.card.titleCard
import com.adytransjaya.ui.screen.login.LoginViewModel

@Composable
fun profileScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
) {
    titleCard(title = "Profil")
}

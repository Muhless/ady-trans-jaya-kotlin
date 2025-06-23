package com.adytransjaya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.adytransjaya.ui.components.splashScreen
import com.adytransjaya.ui.navigation.bottomBar
import com.adytransjaya.ui.screen.delivery.DeliveryScreen
import com.adytransjaya.ui.screen.delivery.DeliveryViewModel
import com.adytransjaya.ui.screen.helpScreen
import com.adytransjaya.ui.screen.history.historyScreen
import com.adytransjaya.ui.screen.homeScreen
import com.adytransjaya.ui.screen.login.LoginViewModel
import com.adytransjaya.ui.screen.login.loginScreen
import com.adytransjaya.ui.screen.profile.profileScreen
import com.adytransjaya.ui.theme.AppColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun MainApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val loginViewModel: LoginViewModel = hiltViewModel()
    val isLoginOrSplash = currentRoute == "login" || currentRoute == "splash"

    Scaffold(
        containerColor = if (isLoginOrSplash) Color.White else AppColors.background,
        bottomBar = {
            if (currentRoute != "login" && currentRoute != "splash") {
                bottomBar(navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding),
        ) {
            composable("splash") { splashScreen(navController) }
            composable("login") { loginScreen(navController, loginViewModel) }
            composable("home") { homeScreen(navController, loginViewModel) }
            composable("delivery/{id}") {
                val deliveryViewModel: DeliveryViewModel = hiltViewModel()
                DeliveryScreen(navController, deliveryViewModel)
            }

            composable("profile") {
                profileScreen(
                    navController,
                    loginViewModel,
                )
            }
            composable("history") { historyScreen(navController) }
            composable("help") { helpScreen(navController) }
        }
    }
}

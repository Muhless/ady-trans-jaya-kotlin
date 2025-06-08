package com.adytransjaya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adytransjaya.ui.components.splashScreen
import com.adytransjaya.ui.navigation.bottomBar
import com.adytransjaya.ui.screen.delivery.deliveryDetailScreen
import com.adytransjaya.ui.screen.delivery.deliveryScreen
import com.adytransjaya.ui.screen.helpScreen
import com.adytransjaya.ui.screen.historyScreen
import com.adytransjaya.ui.screen.homeScreen
import com.adytransjaya.ui.screen.login.LoginViewModel
import com.adytransjaya.ui.screen.login.loginScreen
import com.adytransjaya.ui.screen.profile.ProfileViewModel
import com.adytransjaya.ui.screen.profileScreen
import com.adytransjaya.ui.theme.AppColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val currentBackStackEntry = navController.currentBackStackEntryFlow.collectAsState(null)
            val currentRoute = currentBackStackEntry.value?.destination?.route

            Scaffold(
                containerColor = AppColors.Background,
                bottomBar = {
                    if (currentRoute != "login" && currentRoute != "splash") {
                        bottomBar(navController)
                    }
                },
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "splash",
                    modifier = Modifier.padding(innerPadding),
                ) {
                    composable("splash") { splashScreen(navController) }
                    composable("login") { loginScreen(navController) }
                    composable("home") { backStackEntry ->
                        val loginViewModel: LoginViewModel = hiltViewModel()
                        val profileViewModel: ProfileViewModel = hiltViewModel()
                        homeScreen(navController, loginViewModel, profileViewModel)
                    }
                    composable("delivery") { deliveryScreen(navController) }
                    composable("delivery_detail") { deliveryDetailScreen(navController) }
                    composable("profile") { profileScreen(navController) }
                    composable("history") { historyScreen(navController) }
                    composable("help") { helpScreen(navController) }
                }
            }
        }
    }
}

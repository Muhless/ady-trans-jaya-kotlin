package com.adytransjaya

import BottomBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.adytransjaya.data.repository.DeliveryRepository
import com.adytransjaya.ui.components.splashScreen
import com.adytransjaya.ui.screen.delivery.DeliveryProgressScreen
import com.adytransjaya.ui.screen.delivery.DeliveryScreen
import com.adytransjaya.ui.screen.delivery.DeliveryViewModel
import com.adytransjaya.ui.screen.delivery.detail.DeliveryDetailScreen
import com.adytransjaya.ui.screen.helpScreen
import com.adytransjaya.ui.screen.history.DeliveryHistoryScreen
import com.adytransjaya.ui.screen.homeScreen
import com.adytransjaya.ui.screen.login.LoginViewModel
import com.adytransjaya.ui.screen.login.loginScreen
import com.adytransjaya.ui.screen.profile.profileScreen
import com.adytransjaya.ui.theme.AppColors
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var repository: DeliveryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp(repository)
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun MainApp(repository: DeliveryRepository) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val loginViewModel: LoginViewModel = hiltViewModel()
    val isLoginOrSplash = currentRoute == "login" || currentRoute == "splash"
    val deliveryViewModel: DeliveryViewModel = hiltViewModel()

    Scaffold(
        containerColor = if (isLoginOrSplash) Color.White else AppColors.background,
        bottomBar = {
            if (currentRoute != "login" && currentRoute != "splash") {
                BottomBar(navController)
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
                DeliveryScreen(navController, deliveryViewModel)
            }
            composable("delivery-progress") {
                DeliveryProgressScreen(navController, deliveryViewModel, repository)
            }
            composable("delivery-history") {
                DeliveryHistoryScreen(navController, deliveryViewModel)
            }
            composable("delivery-detail/{id}") { backStackEntry ->
                val deliveryId = backStackEntry.arguments?.getString("id")?.toIntOrNull()

                if (deliveryId != null) {
                    DeliveryDetailScreen(
                        deliveryId,
                        deliveryViewModel,
                        repository,
                        navController,
                    )
                } else {
                    Text("ID pengiriman tidak valid")
                }
            }

            composable("profile") {
                profileScreen(
                    navController,
                    loginViewModel,
                )
            }
            composable("help") { helpScreen(navController) }
        }
    }
}

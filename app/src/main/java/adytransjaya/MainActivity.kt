package adytransjaya

import adytransjaya.ui.navigation.bottomBar
import adytransjaya.ui.screen.deliveryScreen
import adytransjaya.ui.screen.detailScreen
import adytransjaya.ui.screen.helpScreen
import adytransjaya.ui.screen.historyScreen
import adytransjaya.ui.screen.homeScreen
import adytransjaya.ui.screen.profileScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            androidx.compose.foundation.layout.Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(Color(0xFFEF4444)),
            ) {
                Scaffold(
                    bottomBar = { bottomBar(navController) },
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        composable("home") { homeScreen(navController) }
                        composable("delivery") { deliveryScreen(navController) }
                        composable("profile") { profileScreen(navController) }
                        composable("history") { historyScreen(navController) }
                        composable("detail") { detailScreen(navController) }
                        composable("help") { helpScreen(navController) }
                    }
                }
            }
        }
    }
}

package adytransjaya

import adytransjaya.ui.components.splashScreen
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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
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
            val currentBackStackEntry = navController.currentBackStackEntryFlow.collectAsState(null)
            val currentRoute = currentBackStackEntry.value?.destination?.route

            Scaffold(
                containerColor = Color(0xfff7fbfc),
                bottomBar = {
                    if (currentRoute != "splash") {
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

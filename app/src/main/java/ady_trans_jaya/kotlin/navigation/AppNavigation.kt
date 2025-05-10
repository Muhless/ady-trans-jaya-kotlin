package ady_trans_jaya.kotlin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ady_trans_jaya.kotlin.ui.screen.HomeScreen
import ady_trans_jaya.kotlin.ui.screen.DetailScreen
import androidx.compose.ui.Modifier

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(onNavigate = {
                navController.navigate("detail")
            })
        }
        composable("detail") {
            DetailScreen()
        }
    }
}

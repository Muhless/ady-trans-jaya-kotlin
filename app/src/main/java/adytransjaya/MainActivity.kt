package adytransjaya

import adytransjaya.ui.navigation.bottomBar
import adytransjaya.ui.screen.detailScreen
import adytransjaya.ui.screen.homeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(bottomBar = { bottomBar(navController) }) { innerPadding ->
                NavHost(navController = navController, startDestination = "home", modifier = Modifier.padding(innerPadding)) {
                    composable("home") { homeScreen(navController) }
                    composable("detail") { detailScreen(navController) }
                }
            }
        }
    }
}

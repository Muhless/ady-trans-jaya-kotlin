import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adytransjaya.ui.components.login.loginButton
import com.adytransjaya.ui.components.login.logoImage
import com.adytransjaya.ui.components.login.passwordInput
import com.adytransjaya.ui.components.login.usernameInput
import com.adytransjaya.ui.screen.login.LoginViewModel

@Composable
fun loginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val isLoading by remember { derivedStateOf { viewModel.isLoading.value } }
    val loginError by remember { derivedStateOf { viewModel.loginError.value } }
    val token by remember { derivedStateOf { viewModel.token.value } }

    LaunchedEffect(token) {
        if (token != null) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        logoImage()
        usernameInput(username) { username = it }
        Spacer(Modifier.height(8.dp))
        passwordInput(password, passwordVisible, { password = it }) {
            passwordVisible = !passwordVisible
        }
        Spacer(Modifier.height(16.dp))
        loginButton(isLoading) {
            viewModel.login(username, password)
        }
        if (!loginError.isNullOrEmpty()) {
            Text(
                text = loginError.orEmpty(), // ✅ aman, tidak pakai !!
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        helpText()
    }
}

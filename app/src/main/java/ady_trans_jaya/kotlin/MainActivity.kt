package ady_trans_jaya.kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ady_trans_jaya.kotlin.ui.theme.AdytransjayakotlinTheme
import androidx.annotation.InspectableProperty

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdytransjayakotlinTheme {
              HelloWorld()
                }
            }
        }
    }

@Composable
fun HelloWorld(){
    Text(
        text = "Hello World",
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.fillMaxSize().wrapContentSize()
    )
}

@Preview(showBackground = true)
@Composable
fun HelloWorldPreview() {
    AdytransjayakotlinTheme {
        HelloWorld()
    }
}

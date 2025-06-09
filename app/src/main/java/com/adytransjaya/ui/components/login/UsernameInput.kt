package com.adytransjaya.ui.components.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.adytransjaya.ui.theme.AppColors

@Composable
fun usernameInput(
    value: String,
    onChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { Text("Username") },
        singleLine = true,
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "User") },
        colors =
            TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF0F0F0),
                unfocusedContainerColor = Color(0xFFF0F0F0),
                focusedIndicatorColor = AppColors.BrandBlueDark,
                unfocusedIndicatorColor = Color.LightGray,
                focusedLabelColor = AppColors.BrandBlueDark,
            ),
        textStyle = TextStyle(fontSize = 16.sp),
        modifier = Modifier.fillMaxWidth(),
    )
}

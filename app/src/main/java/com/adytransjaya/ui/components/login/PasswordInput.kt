package com.adytransjaya.ui.components.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adytransjaya.ui.theme.AppColors

@Composable
fun passwordInput(
    value: String,
    visible: Boolean,
    onChange: (String) -> Unit,
    onToggleVisibility: () -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { Text("Password") },
        singleLine = true,
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = { Icon(imageVector = Icons.Default.Key, contentDescription = "Password") },
        trailingIcon = {
            val icon = if (visible) Icons.Default.Visibility else Icons.Default.VisibilityOff
            val desc = if (visible) "Hide password" else "Show password"
            IconButton(onClick = onToggleVisibility) {
                Icon(imageVector = icon, contentDescription = desc)
            }
        },
        shape = RoundedCornerShape(12.dp),
        colors =
            TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF0F0F0),
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = AppColors.BrandBlueDark,
                unfocusedIndicatorColor = Color.LightGray,
                focusedLabelColor = Color.LightGray,
            ),
        textStyle = TextStyle(fontSize = 16.sp),
        modifier = Modifier.fillMaxWidth(),
    )
}

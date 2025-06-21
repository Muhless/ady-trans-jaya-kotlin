package com.adytransjaya.ui.components.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
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
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = AppColors.BrandBlueDark,
                unfocusedIndicatorColor = Color.LightGray,
                focusedLabelColor = Color.LightGray,
            ),
        textStyle = TextStyle(fontSize = 16.sp),
        modifier =
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp)),
    )
}

package com.adytransjaya.ui.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adytransjaya.R
import com.adytransjaya.ui.theme.AppColors

@Suppress("ktlint:standard:function-naming")
@Composable
fun MenuList(navController: NavController) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .size(100.dp)
                .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(AppColors.base),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            MenuCard(
                image = R.drawable.icon_location,
                onClick = { navController.navigate("delivery") },
                modifier = Modifier.size(60.dp),
            )
            MenuCard(
                image = R.drawable.icon_search,
                onClick = { navController.navigate("history") },
                modifier = Modifier.size(60.dp),
            )
            MenuCard(
                image = R.drawable.icon_profile,
                onClick = { navController.navigate("profile") },
                modifier = Modifier.size(60.dp),
            )
            MenuCard(
                image = R.drawable.icon_chat,
                onClick = { navController.navigate("help") },
                modifier = Modifier.size(60.dp),
            )
        }
    }
}

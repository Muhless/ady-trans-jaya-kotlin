// package adytransjaya.ui.navigation
//
// import adytransjaya.ui.theme.AppColors
// import androidx.compose.foundation.layout.Arrangement
// import androidx.compose.foundation.layout.Box
// import androidx.compose.foundation.layout.Column
// import androidx.compose.foundation.layout.size
// import androidx.compose.material3.Icon
// import androidx.compose.material3.MaterialTheme
// import androidx.compose.material3.Text
// import androidx.compose.runtime.Composable
// import androidx.compose.ui.Alignment
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.graphics.Color
// import androidx.compose.ui.unit.dp
//
// @Composable
// fun bottomNavItemView(
//    item: BottomNavItem,
//    selected: Boolean,
//    onClick: () -> Unit,
// ) {
//    Box(
//        modifier =
//            Modifier
//                .weight(1f) // penting: bagi rata tiap item
//                .fillMaxHeight() // isi tinggi navigation bar
//                .clickable { onClick() },
//        contentAlignment = Alignment.Center,
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//        ) {
//            Icon(
//                imageVector = item.icon,
//                contentDescription = item.label,
//                modifier = Modifier.size(20.dp),
//                tint = if (selected) AppColors.BrandBlue else Color(0xFF9CA3AF),
//            )
//            Text(
//                text = item.label,
//                fontSize = MaterialTheme.typography.labelSmall.fontSize,
//                color = if (selected) AppColors.BrandBlue else Color(0xFF9CA3AF),
//            )
//        }
//    }
// }

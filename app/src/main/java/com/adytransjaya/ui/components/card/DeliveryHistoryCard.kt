import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adytransjaya.data.model.DeliveryItem
import com.adytransjaya.ui.theme.AppColors
import com.adytransjaya.utils.dateFormatter

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryHistoryCard(
    delivery: DeliveryItem,
    onClick: (DeliveryItem) -> Unit,
) {
    val statusColor =
        when (delivery.deliveryStatus.lowercase()) {
            "dibatalkan" -> AppColors.error
            "selesai" -> AppColors.Success
            else -> AppColors.BrandBlue
        }

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable { onClick(delivery) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.card),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = dateFormatter(delivery.deliveryDate),
                    style = MaterialTheme.typography.titleMedium,
                )

                Text(
                    text = delivery.deliveryStatus.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.bodySmall,
                    color = statusColor,
                )
            }
            Text(
                text = "Tujuan: ${delivery.destinationAddress}",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

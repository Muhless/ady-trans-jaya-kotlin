import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adytransjaya.data.model.DeliveryItem
import com.adytransjaya.ui.theme.AppColors

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryHistoryCard(
    delivery: DeliveryItem,
    onClick: (DeliveryItem) -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable { onClick(delivery) },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.card),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Kode Pengiriman: ${delivery.deliveryCode}",
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = "Tujuan: ${delivery.destinationAddress}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "Status: ${delivery.deliveryStatus.replaceFirstChar { it.uppercase() }}",
                style = MaterialTheme.typography.bodySmall,
                color = AppColors.BrandBlue,
            )
        }
    }
}

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adytransjaya.data.model.DeliveryItem
import com.adytransjaya.ui.utils.getStatusColor

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryDetailCard(delivery: DeliveryItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Delivery ID: ${delivery.id}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )

                delivery.deliveryStatus?.let { status ->
                    Text(
                        text = status.uppercase(),
                        style = MaterialTheme.typography.labelMedium,
                        color = getStatusColor(status),
                        fontWeight = FontWeight.Medium,
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Pelanggan: ${delivery.transaction.customer.name}",
                style = MaterialTheme.typography.bodyMedium,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Pickup: ${delivery.pickupAddress}",
                style = MaterialTheme.typography.bodyMedium,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Destination: ${delivery.destinationAddress}",
                style = MaterialTheme.typography.bodyMedium,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Load Type: ${delivery.loadType}",
                style = MaterialTheme.typography.bodySmall,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Total Weight: ${delivery.totalWeight} kg",
                style = MaterialTheme.typography.bodySmall,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Delivery Date: ${delivery.deliveryDate}",
                style = MaterialTheme.typography.bodySmall,
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

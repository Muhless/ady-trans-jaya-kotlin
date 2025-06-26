import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adytransjaya.data.model.DeliveryItem
import com.adytransjaya.ui.components.Divider
import com.adytransjaya.ui.components.card.delivery.DeliveryList
import com.adytransjaya.ui.theme.AppColors
import com.adytransjaya.utils.dateTimeFormatter

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryProgressCard(delivery: DeliveryItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier =
                Modifier
                    .background(AppColors.card)
                    .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = "Progress Pengiriman",
                style = MaterialTheme.typography.titleMedium,
            )
            Divider()

            val progress = delivery.deliveryProgress.firstOrNull()
            progress?.let {
                DeliveryList(
                    label = "Pengiriman Dimulai",
                    value = dateTimeFormatter(it.deliveryStartTime),
                )
                DeliveryList(
                    label = "Waktu Penjemputan",
                    value = it.pickupTime?.let { dateTimeFormatter(it) } ?: "-",
                )

                DeliveryList(
                    label = "Tiba di Lokasi",
                    value = it.arrivalTime?.let { dateTimeFormatter(it) } ?: "-",
                )

                DeliveryList(
                    label = "Nama Penerima",
                    value = it.receiverName ?: "-",
                )

                DeliveryList(
                    label = "No. Telepon Penerima",
                    value = it.receiverPhone ?: "-",
                )

                DeliveryList(
                    label = "Diterima Pada",
                    value = it.receivedAt?.let { dateTimeFormatter(it) } ?: "-",
                )
            }
        }
    }
}

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adytransjaya.data.model.DeliveryItem
import com.adytransjaya.data.model.DeliveryProgress
import com.adytransjaya.ui.components.Divider
import com.adytransjaya.ui.components.card.delivery.DeliveryList
import com.adytransjaya.ui.theme.AppColors
import com.adytransjaya.utils.dateFormatter

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryProgressCard(
    progress: DeliveryProgress,
    delivery: DeliveryItem,
) {
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

            // Info dari DeliveryItem
            DeliveryList(label = "Alamat Penjemputan", value = delivery.pickupAddress)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("")
                UnderlinedClickableText(
                    label = "lihat lokasi",
                    pickupLat = delivery.pickupAddressLat,
                    pickupLng = delivery.pickupAddressLang,
                )
            }

            DeliveryList(label = "Alamat Tujuan", value = delivery.destinationAddress)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("")
                UnderlinedClickableText(
                    label = "lihat lokasi",
                    deliveryLat = delivery.destinationAddressLat,
                    deliveryLng = delivery.destinationAddressLang,
                )
            }

            DeliveryList(label = "Tanggal Pengiriman", value = dateFormatter(delivery.deliveryDate))
            DeliveryList(
                label = "Batas Pengiriman",
                value = dateFormatter(delivery.deliveryDeadlineDate),
            )
            DeliveryList(label = "Status Pengiriman", value = delivery.deliveryStatus)

            Divider()

            // Info dari DeliveryProgress
            progress.pickupTime?.let {
                DeliveryList(label = "Waktu Penjemputan", value = dateFormatter(it))
            }

            progress.arrivalTime?.let {
                DeliveryList(label = "Tiba di Lokasi", value = dateFormatter(it))
            }

            progress.receiverName?.let {
                DeliveryList(label = "Nama Penerima", value = it)
            }

            progress.receiverPhone?.let {
                DeliveryList(label = "No. Telepon Penerima", value = it)
            }

            progress.receivedAt?.let {
                DeliveryList(label = "Diterima Pada", value = dateFormatter(it))
            }

            progress.pickupPhotoURL?.takeIf { it.isNotBlank() }?.let {
                DeliveryList(label = "Foto Penjemputan", value = "Tersedia")
            }

            progress.deliveryPhotoURL?.takeIf { it.isNotBlank() }?.let {
                DeliveryList(label = "Foto Pengiriman", value = "Tersedia")
            }

            progress.receiverSignature?.takeIf { it.isNotBlank() }?.let {
                DeliveryList(label = "Tanda Tangan", value = "Tersedia")
            }
        }
    }
}

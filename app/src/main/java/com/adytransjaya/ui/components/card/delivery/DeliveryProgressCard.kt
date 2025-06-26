import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adytransjaya.data.model.DeliveryItem
import com.adytransjaya.data.repository.DeliveryRepository
import com.adytransjaya.ui.components.Divider
import com.adytransjaya.ui.components.card.delivery.DeliveryList
import com.adytransjaya.ui.components.upload.UploadDeliveryPhotoButton
import com.adytransjaya.ui.components.upload.UploadPickupPhotoButton
import com.adytransjaya.ui.theme.AppColors
import com.adytransjaya.utils.dateTimeFormatter

@Suppress("ktlint:standard:function-naming")
@Composable
fun DeliveryProgressCard(
    delivery: DeliveryItem,
    repository: DeliveryRepository,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
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
                    label = "Barang Diambil",
                    value = it.pickupTime?.let { dateTimeFormatter(it) } ?: "-",
                )
                if (it.pickupTime == null || it.pickupPhotoURL.isNullOrBlank()) {
                    UploadPickupPhotoButton(
                        deliveryProgressId = it.id,
                        repository = repository,
                        onSuccess = { photoUrl ->
                            Log.d("Upload", "Pickup photo uploaded: $photoUrl")
                            // Refresh data atau update UI state
                        },
                    )
                }

                DeliveryList(
                    label = "Tiba di Lokasi",
                    value = it.arrivalTime?.let { dateTimeFormatter(it) } ?: "-",
                )

                Divider()

                DeliveryList(
                    label = "Nama Penerima",
                    value = if (it.receiverName.isNullOrBlank()) "-" else it.receiverName,
                )

                DeliveryList(
                    label = "No. Telepon Penerima",
                    value = if (it.receiverPhone.isNullOrBlank()) "-" else it.receiverPhone,
                )

                DeliveryList(
                    label = "Diterima Pada",
                    value = it.receivedAt?.let { dateTimeFormatter(it) } ?: "-",
                )

//                if (it.receiverSignatureUrl.isBlank()) {
//                    UploadSignatureButton(
//                        deliveryProgressId = it.id,
//                        repository = repository,
//                        onSuccess = { photoUrl ->
//                            Log.d("Upload", "Signature uploaded: $photoUrl")
//                        }
//                    )
//                }

                // Upload Delivery Photo Button
                if (it.deliveryPhotoURL.orEmpty().isBlank()) {
                    UploadDeliveryPhotoButton(
                        deliveryProgressId = it.id,
                        repository = repository,
                        onSuccess = { photoUrl ->
                            Log.d("Upload", "Delivery photo uploaded: $photoUrl")
                        },
                    )
                }
            }
        }
    }
}

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
import com.adytransjaya.ui.components.card.delivery.progress.ArrivalPhotoSection
import com.adytransjaya.ui.components.card.delivery.progress.PickupPhotoSection
import com.adytransjaya.ui.screen.delivery.DeliveryViewModel
import com.adytransjaya.ui.theme.AppColors
import com.adytransjaya.utils.dateTimeFormatter
import com.adytransjaya.utils.toFullUrl

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProgressInfoCard(
    delivery: DeliveryItem,
    repository: DeliveryRepository,
    viewModel: DeliveryViewModel,
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

            delivery.deliveryProgress.firstOrNull()?.let { progress ->
                DeliveryList(
                    label = "Mulai Pengiriman",
                    value = dateTimeFormatter(progress.deliveryStartTime),
                )
                DeliveryList(
                    label = "Penjemputan Barang",
                    value = progress.pickupTime?.let { dateTimeFormatter(it) } ?: "-",
                )

                progress.pickupPhotoURL?.let { photoUrl ->
                    PickupPhotoSection(
                        pickupPhotoURL = toFullUrl(photoUrl),
                        deliveryProgressId = progress.id,
                        repository = repository,
                        viewModel = viewModel,
                    )
                }

                if (progress.pickupTime != null) {
                    DeliveryList(
                        label = "Tiba di Lokasi Tujuan",
                        value = progress.arrivalTime?.let { dateTimeFormatter(it) } ?: "-",
                    )

                    progress.arrivalPhotoURL?.let { photoUrl ->
                        ArrivalPhotoSection(
                            arrivalPhotoURL = toFullUrl(photoUrl),
                            deliveryProgressId = progress.id,
                            repository = repository,
                            viewModel = viewModel,
                        )
                    }
                }
            } ?: Text("Data progres belum tersedia")
        }
    }
}

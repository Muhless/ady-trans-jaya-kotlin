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
fun DeliveryProgressCard(
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

            val progress = delivery.deliveryProgress.firstOrNull()
            progress?.let {
                DeliveryList(
                    label = "Mulai Pengiriman",
                    value = dateTimeFormatter(it.deliveryStartTime),
                )
                DeliveryList(
                    label = "Penjemputan Barang",
                    value = it.pickupTime?.let { dateTimeFormatter(it) } ?: "-",
                )
                PickupPhotoSection(
                    pickupPhotoURL = toFullUrl(it.pickupPhotoURL),
                    deliveryProgressId = it.id,
                    repository = repository,
                    viewModel = viewModel,
                )

                if (it.pickupTime != null) {
                    DeliveryList(
                        label = "Tiba di Lokasi Tujuan",
                        value = it.arrivalTime?.let { dateTimeFormatter(it) } ?: "-",
                    )
                    ArrivalPhotoSection(
                        arrivalPhotoURL = toFullUrl(it.arrivalPhotoURL),
                        deliveryProgressId = it.id,
                        repository = repository,
                        viewModel = viewModel,
                    )
                }
                Divider()
                DeliveryList(
                    label = "Batas Pengiriman",
                    value = dateTimeFormatter(delivery.deliveryDeadlineDate),
                )
            }
        }
    }
}

package com.adytransjaya.ui.components.card.delivery.progress

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.adytransjaya.data.repository.DeliveryRepository
import com.adytransjaya.ui.components.upload.UploadArrivalPhotoButton
import com.adytransjaya.ui.screen.delivery.DeliveryViewModel
import com.adytransjaya.ui.theme.AppColors

@Suppress("ktlint:standard:function-naming")
@Composable
fun ArrivalPhotoSection(
    arrivalPhotoURL: String?,
    deliveryProgressId: Int,
    repository: DeliveryRepository,
    viewModel: DeliveryViewModel,
) {
    var showDialog by remember { mutableStateOf(false) }

    if (!arrivalPhotoURL.isNullOrBlank()) {
        AsyncImage(
            model = arrivalPhotoURL,
            contentDescription = "Foto Barang",
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(AppColors.card)
                    .clickable { showDialog = true },
            contentScale = ContentScale.Crop,
        )

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(Color.Black)
                            .padding(16.dp),
                ) {
                    AsyncImage(
                        model = arrivalPhotoURL,
                        contentDescription = "Foto Barang (Full)",
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Fit,
                    )
                    TextButton(
                        onClick = { showDialog = false },
                        modifier = Modifier.align(Alignment.TopEnd),
                    ) {
                        Text("Tutup", color = Color.White)
                    }
                }
            }
        }
    } else {
        UploadArrivalPhotoButton(
            deliveryProgressId = deliveryProgressId,
            repository = repository,
            onSuccess = { photoUrl ->
                Log.d("Upload", "Pickup photo uploaded: $photoUrl")
                viewModel.getActiveDelivery()
            },
        )
    }
}

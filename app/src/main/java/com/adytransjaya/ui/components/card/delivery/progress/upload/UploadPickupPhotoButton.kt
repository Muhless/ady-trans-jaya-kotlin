package com.adytransjaya.ui.components.upload

import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.adytransjaya.data.repository.DeliveryRepository
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalPermissionsApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun UploadPickupPhotoButton(
    deliveryProgressId: Int,
    repository: DeliveryRepository,
    modifier: Modifier = Modifier,
    onSuccess: ((String) -> Unit)? = null,
    onError: ((String) -> Unit)? = null,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var isUploading by remember { mutableStateOf(false) }
    val cameraPermission = rememberPermissionState(android.Manifest.permission.CAMERA)

    val photoUri = remember { mutableStateOf<Uri?>(null) }

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
        ) { success ->
            if (success && photoUri.value != null) {
                isUploading = true
                coroutineScope.launch {
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(context.contentResolver, photoUri.value)
                    repository.uploadPickupPhoto(deliveryProgressId, bitmap).fold(
                        onSuccess = { photoUrl ->
                            isUploading = false
                            Toast
                                .makeText(context, "Foto berhasil diupload", Toast.LENGTH_SHORT)
                                .show()
                            onSuccess?.invoke(photoUrl)
                        },
                        onFailure = { error ->
                            isUploading = false
                            Toast
                                .makeText(
                                    context,
                                    "Gagal upload: ${error.message}",
                                    Toast.LENGTH_SHORT,
                                ).show()
                            onError?.invoke(error.message ?: "Unknown error")
                        },
                    )
                }
            }
        }

    Button(
        onClick = {
            if (cameraPermission.status.isGranted) {
                val photoFile = File.createTempFile("pickup_", ".jpg", context.cacheDir)
                val uri =
                    FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.fileprovider",
                        photoFile,
                    )
                photoUri.value = uri
                launcher.launch(uri)
            } else {
                cameraPermission.launchPermissionRequest()
            }
        },
        enabled = !isUploading,
        modifier = modifier,
    ) {
        if (isUploading) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CircularProgressIndicator(modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                Text("Uploading...")
            }
        } else {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Default.CameraAlt,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                    )
                    Text("Ambil Foto")
                }
            }
        }
    }
}

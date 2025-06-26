package com.adytransjaya.utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun checkAndRequestCameraPermission(
    activity: Activity,
    onGranted: () -> Unit,
) {
    if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CAMERA)
        == PackageManager.PERMISSION_GRANTED
    ) {
        onGranted()
    } else {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(android.Manifest.permission.CAMERA),
            1001, // requestCode
        )
    }
}

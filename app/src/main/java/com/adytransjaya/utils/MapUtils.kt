package com.adytransjaya.utils

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

object MapUtils {
    fun openLocation(
        context: Context,
        lat: Double,
        lng: Double,
    ) {
        val uri = "https://www.google.com/maps/search/?api=1&query=$lat,$lng".toUri()
        val intent =
            Intent(Intent.ACTION_VIEW, uri).apply {
                setPackage("com.google.android.apps.maps")
            }
        context.startActivity(intent)
    }

    fun openRouteToDestination(
        context: Context,
        destinationLat: Double,
        destinationLng: Double,
    ) {
        val uri =
            (
                "https://www.google.com/maps/dir/?api=1" +
                    "&destination=$destinationLat,$destinationLng&travelmode=driving"
            ).toUri()
        val intent =
            Intent(Intent.ACTION_VIEW, uri).apply {
                setPackage("com.google.android.apps.maps")
            }
        context.startActivity(intent)
    }

    fun openRouteFromOriginToDestination(
        context: Context,
        originLat: Double,
        originLng: Double,
        destLat: Double,
        destLng: Double,
    ) {
        val uri =
            (
                "https://www.google.com/maps/dir/?api=1" +
                    "&origin=$originLat,$originLng" +
                    "&destination=$destLat,$destLng&travelmode=driving"
            ).toUri()
        val intent =
            Intent(Intent.ACTION_VIEW, uri).apply {
                setPackage("com.google.android.apps.maps")
            }
        context.startActivity(intent)
    }
}

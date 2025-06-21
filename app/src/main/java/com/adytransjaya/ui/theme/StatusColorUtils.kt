package com.adytransjaya.ui.utils

import androidx.compose.ui.graphics.Color
import com.adytransjaya.ui.theme.AppColors

fun getStatusColor(status: String): Color =
    when (status.lowercase()) {
        "selesai" -> AppColors.Success
        "dalam pengiriman" -> AppColors.BrandBlue
        "menunggu pengemudi" -> AppColors.Warning
        else -> AppColors.NeutralText
    }

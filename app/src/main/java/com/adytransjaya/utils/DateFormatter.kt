package com.adytransjaya.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun dateFormatter(date: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
    val date = inputFormat.parse(date)
    return outputFormat.format(date!!)
}

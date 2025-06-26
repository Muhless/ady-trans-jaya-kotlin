package com.adytransjaya.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun dateTimeFormatter(date: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("HH:mm, dd MMMM yyyy", Locale("id", "ID"))
    val parsedDate = inputFormat.parse(date)
    return outputFormat.format(parsedDate!!)
}

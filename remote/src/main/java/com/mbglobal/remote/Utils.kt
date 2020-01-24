package com.mbglobal.remote

import java.text.SimpleDateFormat
import java.util.*

fun getDateFromDateTime(dateTime: String): String{
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(parser.parse(dateTime))
}

fun getTimeFromDateTime(dateTime: String): String{
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return formatter.format(parser.parse(dateTime))
}
@file:JvmName("Converter")
package com.example.reahometime.util

import android.annotation.SuppressLint
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*



fun getTimeFromDate(dotNetDate: String): String {
    val date = dateFromDotNetDate(dotNetDate)
    date?.let {
        return formatDateToString(it)
    }
    return "N/A"
}

@SuppressLint("SimpleDateFormat")
fun formatDateToString(date: Date): String {
    return try {
        val formatter = SimpleDateFormat("HH:mm")
        formatter.timeZone = TimeZone.getDefault()
        formatter.format(date)
    } catch (e: Exception) {
        ""
    }
}

fun dateFromDotNetDate(dotNetDate: String): Date? {
    return try {
        val startIndex = dotNetDate.indexOf("(") + 1
        val endIndex = dotNetDate.indexOf("+")
        val date = dotNetDate.substring(startIndex, endIndex)
        val unixTime = date.toLong()
        Date(unixTime)
    } catch (e: Exception) {
        null
    }
}

fun getEstimateTime(dotNetDate: String, timeMillis: Long): String {
    val startDate = dateFromDotNetDate(dotNetDate)
    startDate?.let {
        val estimateTimeLong = it.time - timeMillis
        return "(${estimateTimeLong / (60*1000)}m)"
    }
    return "N/A"
}

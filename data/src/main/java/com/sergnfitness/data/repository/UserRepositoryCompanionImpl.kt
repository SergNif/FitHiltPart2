package com.sergnfitness.data.repository

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

class UserRepositoryCompanionImpl {
    fun converStringToData(dt: String, i: Long): String? {
        var date85: Date?
        val formatter25 = SimpleDateFormat("dd MMMM yyyy")
        try {
            val formatter2 = DateTimeFormatter.ofPattern("d MMMM yyyy")
//            Log.e(taG, "conv 1 ${dt}")
            val date2 = LocalDate.parse(dt, formatter2).plusDays(i)
//            Log.e(taG, "conv 2 ${date2}")

            val formatter15 = SimpleDateFormat("yyyy-MM-dd")
            //  "EEE, MMM d, ''yy"
            date85 = formatter15.parse(date2.toString())
            SimpleDateFormat("dd MMMM yyyy")

        } catch (e: DateTimeParseException) {
            val formatter15 = SimpleDateFormat("yyyy-MM-dd")
            //  "EEE, MMM d, ''yy"
            date85 = formatter15.parse(dt.toString())
        }
        return date85?.let { formatter25.format(it) }
    }
    companion object{

    }
}
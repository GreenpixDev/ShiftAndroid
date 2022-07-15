package ru.cft.shift.scheduler.data

import java.util.*

data class Day(
    val yearNumber: Int,
    val monthNumber: Int,
    val dayNumber: Int
    ) {

    val calendar: Calendar by lazy {
        val date = Calendar.getInstance()
        date.set(yearNumber, monthNumber, dayNumber, 0, 0, 0)
        date
    }

    val month: Month by lazy { Month(yearNumber, monthNumber) }
}

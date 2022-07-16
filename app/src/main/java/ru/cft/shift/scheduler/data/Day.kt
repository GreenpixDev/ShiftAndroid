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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Day

        if (yearNumber != other.yearNumber) return false
        if (monthNumber != other.monthNumber) return false
        if (dayNumber != other.dayNumber) return false

        return true
    }

    override fun hashCode(): Int {
        var result = yearNumber
        result = 31 * result + monthNumber
        result = 31 * result + dayNumber
        return result
    }
}

package ru.cft.shift.scheduler.data

import java.util.*

data class Month(
    val yearNumber: Int,
    val monthNumber: Int,
    ) {

    val calendar: Calendar by lazy {
        val date = Calendar.getInstance()
        date.set(yearNumber, monthNumber, 0, 0, 0, 0)
        date
    }

    fun next(): Month {
        val month = Math.floorMod(monthNumber + 1, 12)
        val year = yearNumber + Math.floorDiv(month + 1, 12)
        return Month(year, month)
    }

    fun previous(): Month {
        val month = Math.floorMod(monthNumber - 1, 12)
        val year = yearNumber + Math.floorDiv(month - 1, 12)
        return Month(year, month)
    }
}
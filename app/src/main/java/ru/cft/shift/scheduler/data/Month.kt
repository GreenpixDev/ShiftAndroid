package ru.cft.shift.scheduler.data

import java.util.*

data class Month(
    val year: Int,
    val month: Int,
    ) {

    val calendar: Calendar by lazy {
        val date = Calendar.getInstance()
        date.set(year, month, 0)
        date
    }

    fun next(): Month {
        val month = Math.floorMod(month + 1, 12)
        val year = year + Math.floorDiv(month + 1, 12)
        return Month(year, month)
    }

    fun previous(): Month {
        val month = Math.floorMod(month - 1, 12)
        val year = year + Math.floorDiv(month - 1, 12)
        return Month(year, month)
    }
}
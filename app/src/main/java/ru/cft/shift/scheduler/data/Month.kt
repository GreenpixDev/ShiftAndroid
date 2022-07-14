package ru.cft.shift.scheduler.data

data class Month(
    val year: Int,
    val month: Int,
    ) {

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
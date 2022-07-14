package ru.cft.shift.scheduler.dto

data class DateRequest(
    val startDate: String,
    val endDate: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DateRequest

        if (startDate != other.startDate) return false
        if (endDate != other.endDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = startDate.hashCode()
        result = 31 * result + endDate.hashCode()
        return result
    }
}
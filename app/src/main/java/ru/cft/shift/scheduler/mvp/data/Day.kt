package ru.cft.shift.scheduler.mvp.data

data class Day(
    val month: Int,
    val events: Collection<Event>
)

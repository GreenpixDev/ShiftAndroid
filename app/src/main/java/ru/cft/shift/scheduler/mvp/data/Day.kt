package ru.cft.shift.scheduler.mvp.data

import java.util.*

data class Day(
    val date: Calendar,
    val events: Collection<Event>
    )

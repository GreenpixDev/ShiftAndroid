package ru.cft.shift.scheduler.data

import ru.cft.shift.scheduler.dto.EventColor
import java.util.*

data class Event(
    val id: Long,
    val name: String,
    val begin: Date,
    val end: Date,
    val color: EventColor,
    val type: String
    )
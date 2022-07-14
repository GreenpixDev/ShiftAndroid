package ru.cft.shift.scheduler.data

import java.util.*

data class Event(
    var begin: Date,
    var end: Date,
    var mark: Mark
    )
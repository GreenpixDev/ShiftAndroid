package ru.cft.shift.scheduler.dto

data class EventInfoRequest(
    val dateRequest: DateRequest,
    val name: String,
    val type: String,
    val color: EventColor
)
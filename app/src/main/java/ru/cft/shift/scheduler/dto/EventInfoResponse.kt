package ru.cft.shift.scheduler.dto

data class EventInfoResponse(
    val dateRequest: DateRequest,
    val id: Long,
    val type: EventType,
    val color: EventColor
)
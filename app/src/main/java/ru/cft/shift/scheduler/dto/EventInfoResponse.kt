package ru.cft.shift.scheduler.dto

data class EventInfoResponse(
    val name: String,
    val dateRequest: DateRequest,
    val id: Long,
    val type: String,
    val color: EventColor
)
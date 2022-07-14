package ru.cft.shift.scheduler.dto

data class EventInfoResponse(
    val dateRequest: DateRequest,
    val id: Long,
    val name: String,
    val description: String
)
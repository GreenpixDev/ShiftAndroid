package ru.cft.shift.scheduler.dto

data class PasswordRequest(
    val token: String,
    val password: String
)
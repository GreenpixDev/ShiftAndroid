package ru.cft.shift.scheduler.dto

data class SignupRequest(
    val password: String,
    val login: String,
    val email: String
)
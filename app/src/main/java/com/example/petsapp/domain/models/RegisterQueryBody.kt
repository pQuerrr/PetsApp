package com.example.petsapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterQueryBody(
    val login: String,
    val password: String,
    val email: String,
    val username: String
)

package com.example.petsapp.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class LoginQueryBody(
    val login: String,
    val password: String
)

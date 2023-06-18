package com.example.petsapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ProfileQueryBody(
    val token: String
)

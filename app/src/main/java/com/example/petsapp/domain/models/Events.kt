package com.example.petsapp.domain.models

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.petsapp.R
import java.time.LocalDateTime

data class Events(
    val name: String = "",
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val creatorUsername: String = "",
    val description: String = "",
    val image: Painter
)
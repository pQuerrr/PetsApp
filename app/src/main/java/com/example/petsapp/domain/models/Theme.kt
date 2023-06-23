package com.example.petsapp.domain.models

import android.os.Build
import android.os.Message
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

data class Theme(
    val name: String = "",
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val creatorUsername: String = "",
    val description: String = ""
)

data class ThemeMessage(
    val message: String = "",
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val creatorUsername: String = ""
)
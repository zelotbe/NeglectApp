package com.example.neglectapp.core

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

fun formatTime(seconds: String, minutes: String, hours: String): String {
    return "$hours:$minutes:$seconds"
}

fun Int.pad(): String {
    return this.toString().padStart(2, '0')
}

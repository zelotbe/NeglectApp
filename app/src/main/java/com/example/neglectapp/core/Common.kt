package com.example.neglectapp.core

import java.time.Duration
import java.time.LocalTime

fun calculateProgress(startHour: String, endHour: String): Float {
    val start = LocalTime.parse(startHour)
    val end = LocalTime.parse(endHour)
    val currentTime = LocalTime.now()

    val totalDuration = Duration.between(start, end).toMillis()
    val elapsedDuration = Duration.between(start, currentTime).toMillis()

    return (elapsedDuration.toFloat() / totalDuration.toFloat())

}
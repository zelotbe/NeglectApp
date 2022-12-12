package com.example.neglectapp.util

import java.time.LocalTime

fun formatTime(seconds: String, minutes: String, hours: String): String {
    return "$hours:$minutes:$seconds"
}

fun Int.pad(): String {
    return this.toString().padStart(2, '0')
}

fun calculateSeconds(time: LocalTime): Int {
    val timeHour = time.hour
    val timeMinutes = time.minute
    val timeSeconds = time.second
    var hour = 0
    var minutes = 0
    var seconds = 0
    hour = timeHour * 3600
    minutes = timeMinutes * 60
    seconds = timeSeconds
    return hour + minutes + seconds
}
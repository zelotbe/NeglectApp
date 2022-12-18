package com.example.neglectapp.components.display

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CircularProgressIndicator
import com.example.neglectapp.data.datastore.StoreSessions
import java.time.Duration
import java.time.LocalTime

@Composable
fun DisplayProgress(sessionStore: StoreSessions){

    @Composable
    fun calculateProgress(): Float {
        val startHour =
            sessionStore.getStart.collectAsState(initial = LocalTime.of(7, 30).toString()).value
        val endHour =
            sessionStore.getEnd.collectAsState(initial = LocalTime.of(18, 0).toString()).value

        val start = LocalTime.parse(startHour)
        val end = LocalTime.parse(endHour)
        val currentTime = LocalTime.now()

        val totalDuration = Duration.between(start, end)
        val currentDuration = Duration.between(currentTime, end)
        val duration = (end.toSecondOfDay() - start.toSecondOfDay())
        val current = currentTime.toSecondOfDay()
        val percentage = current.toDouble() / duration * 100
//        Log.d("Duration:", "$duration")
//        Log.d("Current Time:", "$currentTime")
//        Log.d("Duration between now and end:", "$durationTillEnd")
//        Log.d("Calculation: ", "${currentTime.minute} / ${end.minute.toFloat()} = ${end.minute / durationTillEnd.toFloat()}")
//
//        Log.d("Progress:", "${(durationTillEnd / 100.toFloat())}")
        Log.d("Percentage?: ", "$percentage")
        return (currentTime.minute / end.minute.toFloat())

    }

//    Log.d("progress:", "${calculateProgress()}")
    CircularProgressIndicator( progress = calculateProgress(),
        modifier = Modifier.fillMaxSize(),
        strokeWidth = 5.dp)
}


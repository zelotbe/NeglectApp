package com.example.neglectapp.components.display

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.CircularProgressIndicator
import com.example.neglectapp.viewmodel.HeftosViewModel
import java.time.Duration
import java.time.LocalTime

@Composable
fun DisplayProgress(
    viewModel: HeftosViewModel = viewModel()
){
    val startHour = viewModel.startHour.collectAsState(initial = "09:30").value
    val endHour = viewModel.endHour.collectAsState(initial = "19:00").value
    @Composable
    fun calculateProgress(): Float {
        val start = LocalTime.parse(startHour)
        val end = LocalTime.parse(endHour)
        val currentTime = LocalTime.now()

        val totalDuration = Duration.between(start, end).toMillis()
        val elapsedDuration = Duration.between(start, currentTime).toMillis()

        val percentage = (elapsedDuration.toFloat() / totalDuration.toFloat())

        Log.d("Percentage:", "$percentage")

        return percentage

    }
    CircularProgressIndicator( progress = calculateProgress(),
        modifier = Modifier.fillMaxSize(),
        strokeWidth = 5.dp)
}


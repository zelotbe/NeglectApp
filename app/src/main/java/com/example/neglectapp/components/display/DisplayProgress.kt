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
@Composable
fun DisplayProgress(viewModel: HeftosViewModel = viewModel()){
    val scope = rememberCoroutineScope()

//    @Composable
//    fun calculateProgress(): Float {
//        val start = LocalTime.parse(startHour)
//        val end = LocalTime.parse(endHour)
//        val currentTime = LocalTime.now()
//
//        val totalDuration = Duration.between(start, end)
//        val currentDuration = Duration.between(currentTime, end)
//        val duration = (end.toSecondOfDay() - start.toSecondOfDay())
//        val current = currentTime.toSecondOfDay()
//        val percentage = current.toDouble() / duration * 100
//        Log.d("Duration:", "$duration")
//        Log.d("Current Time:", "$currentTime")
//        Log.d("Duration between now and end:", "$durationTillEnd")
//        Log.d("Calculation: ", "${currentTime.minute} / ${end.minute.toFloat()} = ${end.minute / durationTillEnd.toFloat()}")
//
//        Log.d("Progress:", "${(durationTillEnd / 100.toFloat())}")
//
//        return (currentTime.minute / end.minute.toFloat())
//
//    }

    val endHour = viewModel.endHour.toString()
    Log.d("Startuur: ", viewModel.startHour.collectAsState().value)
//    Log.d("progress:", "${calculateProgress()}")
    CircularProgressIndicator( progress = 0.1F,
        modifier = Modifier.fillMaxSize(),
        strokeWidth = 5.dp)
}


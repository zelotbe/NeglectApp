package com.example.neglectapp.components.display

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.CircularProgressIndicator
import com.example.neglectapp.core.calculateProgress
import com.example.neglectapp.viewmodel.HeftosViewModel

@Composable
fun DisplayProgress(
    viewModel: HeftosViewModel = viewModel()
) {
    val startHour = viewModel.startHour.collectAsState(initial = "09:30").value
    val endHour = viewModel.endHour.collectAsState(initial = "19:00").value

    CircularProgressIndicator(
        progress = calculateProgress(startHour, endHour),
        modifier = Modifier.fillMaxSize(),
        strokeWidth = 5.dp
    )
}


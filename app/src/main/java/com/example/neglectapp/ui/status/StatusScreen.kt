package com.example.neglectapp.ui.status

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Text
import com.example.neglectapp.service.SessionState
import com.example.neglectapp.viewmodel.HeftosViewModel

@Composable
fun DisplayStatus(
    status: SessionState,
    modifier: Modifier,
){
    val viewModel: HeftosViewModel = viewModel()

    when (status) {
        SessionState.Started, SessionState.Stopped -> {
            Text("${viewModel.startHour.collectAsState().value} - ${viewModel.endHour.collectAsState().value}")
        }
        SessionState.ClosedOperatingHours -> {
            Text("Tot morgen")
            Text("om ${viewModel.startHour.collectAsState().value}!")
        }
        else -> {
            Text("Geen actieve sessie")
        }
    }
}
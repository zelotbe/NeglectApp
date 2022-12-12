package com.example.neglectapp.components.display

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CircularProgressIndicator
import com.example.neglectapp.data.datastore.StoreSessions
import com.example.neglectapp.util.calculateSeconds
import java.time.LocalTime

@Composable
fun DisplayProgress(sessionStore: StoreSessions){
    val startHour = sessionStore.getStart.collectAsState(initial = LocalTime.of(7,30).toString()).value
    val endHour = sessionStore.getEnd.collectAsState(initial = LocalTime.of(16,0).toString()).value
    val startSeconds = calculateSeconds(LocalTime.parse(startHour))
    val endSeconds = calculateSeconds(LocalTime.parse(endHour))
    val diffHours = endSeconds - startSeconds
    CircularProgressIndicator( progress = 0.4f,
        modifier = Modifier.fillMaxSize(),
        strokeWidth = 5.dp)
}

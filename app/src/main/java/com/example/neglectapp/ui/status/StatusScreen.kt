package com.example.neglectapp.ui.status

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import com.example.neglectapp.components.display.DisplayProgress
import com.example.neglectapp.data.datastore.StoreSessions
import com.example.neglectapp.util.SessionState
import java.time.LocalTime

@Composable
fun DisplayStatus(
    status: SessionState,
    modifier: Modifier,
    sessionStore: StoreSessions

){
    val startHour = sessionStore.getStart.collectAsState(initial = LocalTime.of(7,30) )
    val endHour = sessionStore.getEnd.collectAsState(initial = LocalTime.of(16,0))

    if (status == SessionState.Started || status == SessionState.Stopped){
        Text("${startHour.value} - ${endHour.value}")
    }else{
        Text("Geen actieve sessie")
    }
}
package com.example.neglectapp.ui.landing

import NeglectButton
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.MaterialTheme
import com.example.neglectapp.components.display.DisplayProgress
import com.example.neglectapp.components.settings.SettingsIcon
import com.example.neglectapp.data.datastore.StoreSessions
import com.example.neglectapp.ui.status.DisplayStatus
import com.example.neglectapp.util.*
import com.example.neglectapp.util.Constants.ACTION_SERVICE_START
import com.example.neglectapp.util.Constants.ACTION_SERVICE_STOP
import java.time.LocalTime

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DisplayLanding(
    navController: NavHostController,
    modifier: Modifier,
    sessionService: SessionService
){
    var context = LocalContext.current
    val currentState by sessionService.currentState
    val sessionStore = StoreSessions(context)
    val startHour = sessionStore.getStart.collectAsState(initial = LocalTime.of(7,30).toString() )
    val endHour = sessionStore.getEnd.collectAsState(initial = LocalTime.of(16,0).toString())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier,
        ) {

            DisplayProgress(sessionStore = sessionStore)
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SettingsIcon(navController = navController)
                Spacer(modifier = Modifier.height(60.dp))
                DisplayStatus(modifier = Modifier, status = currentState, sessionStore = sessionStore)
                Spacer(modifier = Modifier.height(30.dp))
                Column(modifier = Modifier.size(45.dp)) {
                    NeglectButton(
                        modifier = Modifier,
                        contentDescription = if(currentState == SessionState.Idle || currentState == SessionState.Stopped)  "Starten" else "Pauzeren",
                        icon = if(currentState == SessionState.Idle || currentState == SessionState.Stopped)  Icons.Default.PlayArrow else Icons.Default.Pause,
                        onClick = {
                            Log.d("LANDING:", "CLICKED")
                            ServiceHelper.triggerForegroundService(
                                context = context,
                                action = if (currentState == SessionState.Started) ACTION_SERVICE_STOP
                                else ACTION_SERVICE_START
                            );Log.d("LANDING:", "SESSION STARTED/PAUSED")
                        }
                    )
                }
            }
        }
    }
}
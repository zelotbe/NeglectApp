package com.example.neglectapp.ui.landing

import NeglectButton
import android.se.omapi.Session
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import com.example.neglectapp.components.display.DisplayProgress
import com.example.neglectapp.components.settings.SettingsIcon
import com.example.neglectapp.data.datastore.StoreSessions
import com.example.neglectapp.ui.status.DisplayStatus
import com.example.neglectapp.util.*
import com.example.neglectapp.util.Constants.ACTION_SERVICE_CANCEL
import com.example.neglectapp.util.Constants.ACTION_SERVICE_START
import com.example.neglectapp.util.Constants.ACTION_SERVICE_STOP
import com.example.neglectapp.util.Constants.ACTION_TRIGGER_ALARM
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
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val color = if (currentState !== SessionState.Canceled) Color.Blue else Color.Gray
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
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier) {
                    NeglectButton(
                        modifier = Modifier,
                        contentDescription = if(currentState == SessionState.Idle || currentState == SessionState.Stopped)  "Starten" else "Pauzeren",
                        icon = if(currentState == SessionState.Idle || currentState == SessionState.Stopped)  Icons.Default.PlayArrow else Icons.Default.Pause,
                        onClick = {
                            ServiceHelper.triggerForegroundService(
                                context = context,
                                action = if (currentState == SessionState.Started) ACTION_SERVICE_STOP
                                else ACTION_SERVICE_START
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                    NeglectButton(
                        modifier = Modifier,
                        enabled = currentState!== SessionState.Idle,
                        interactionSource = interactionSource,
                        contentDescription = "Stoppen",
                        icon = Icons.Default.Stop,
                        onClick = {
                            Log.d("StopButton:", "clicked");ServiceHelper.triggerForegroundService(
                                context = context,
                                action = ACTION_SERVICE_CANCEL
                            );
                        }
                    )
                    NeglectButton(
                        modifier = Modifier,
                        contentDescription = "Stoppen",
                        icon = Icons.Default.Alarm,
                        onClick = {
                            Log.d("AlarmButton:", "clicked");ServiceHelper.triggerForegroundService(
                            context = context,
                            action = ACTION_TRIGGER_ALARM
                        )
                        }
                    )
                }
            }
        }
    }
}
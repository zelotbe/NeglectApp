package com.example.neglectapp.ui.landing

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import com.example.neglectapp.components.display.DisplayProgress
import com.example.neglectapp.components.settings.SettingsIcon
import com.example.neglectapp.ui.status.DisplayStatus
import com.example.neglectapp.util.*
import com.example.neglectapp.util.Constants.ACTION_SERVICE_CANCEL
import com.example.neglectapp.util.Constants.ACTION_SERVICE_START
import com.example.neglectapp.util.Constants.ACTION_SERVICE_STOP
import com.example.neglectapp.util.Constants.ACTION_TRIGGER_ALARM
import com.example.neglectapp.viewmodel.HeftosViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DisplayLanding(
    navController: NavHostController,
    modifier: Modifier,
    sessionService: SessionService
){
    var context = LocalContext.current
    val currentState by sessionService.currentState
    val viewModel: HeftosViewModel = viewModel()
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier,
        ) {

            DisplayProgress()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SettingsIcon(navController = navController)
                Spacer(modifier = Modifier.height(60.dp))
                DisplayStatus(modifier = Modifier, status = currentState)
                Spacer(modifier = Modifier.height(15.dp))
//                Text("${viewmodel.startHour}")
                Row(modifier = Modifier) {

                    Button(onClick = {
                        ServiceHelper.triggerForegroundService(
                            context = context,
                            action = if (currentState == SessionState.Started) ACTION_SERVICE_STOP
                            else ACTION_SERVICE_START
                        )
                    }, modifier = Modifier) {
                        Icon(
                            if(currentState == SessionState.Idle || currentState == SessionState.Stopped)  Icons.Default.PlayArrow else Icons.Default.Pause,
                            contentDescription = if(currentState == SessionState.Idle || currentState == SessionState.Stopped)  "Starten" else "Pauzeren",
                            modifier = Modifier.size(width = 35.dp, height = 35.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(25.dp))
                    Button(onClick = {
                        Log.d("StopButton:", "clicked");ServiceHelper.triggerForegroundService(
                        context = context,
                        action = ACTION_SERVICE_CANCEL
                    );
                    }, modifier = Modifier, interactionSource = interactionSource, enabled = currentState!== SessionState.Idle,) {
                        Icon(
                            Icons.Default.Stop,
                            contentDescription = "Stoppen",
                            modifier = Modifier.size(width = 35.dp, height = 35.dp)
                        )
                    }
                    Button(onClick = {
                        Log.d("AlarmButton:", "clicked");ServiceHelper.triggerForegroundService(
                        context = context,
                        action = ACTION_TRIGGER_ALARM
                    )
                    }, modifier = Modifier) {
                        Icon(
                            Icons.Default.Alarm,
                            contentDescription = "Alarm",
                            modifier = Modifier.size(width = 35.dp, height = 35.dp)
                        )
                    }
                }
            }
        }
    }
}
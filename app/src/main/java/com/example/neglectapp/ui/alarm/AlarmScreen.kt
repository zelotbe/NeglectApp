package com.example.neglectapp.ui.alarm

import android.app.Activity
import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.neglectapp.data.datastore.StoreSessions
import com.example.neglectapp.data.datastore.StoreStimula
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DisplayAlarm(
    modifier: Modifier = Modifier,
){
    var context = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    val stimulaStore = StoreStimula(context)
    val getLight by stimulaStore.getLight.collectAsState(initial = false)
    val sessionStore = StoreSessions(context)
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Black,
        targetValue = Color.White,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    var secondsToDisappear by remember { mutableStateOf(15) }

    LaunchedEffect(Unit) {
        while (secondsToDisappear > 0) {
            delay(1.seconds)
            secondsToDisappear -= 1
        }
        activity?.finish()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if(getLight == true){color} else {
                MaterialTheme.colors.background
            }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text("Heftos Alarm")
        Spacer(modifier = Modifier.height(50.dp))

        Text("Tijd resterend: $secondsToDisappear")
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = { activity?.finish()}, modifier = Modifier) {
            Icon(
                Icons.Default.Check,
                contentDescription = "Alarm accepteren",
                modifier = Modifier.size(width = 35.dp, height = 35.dp)
            )
        }
    }
}

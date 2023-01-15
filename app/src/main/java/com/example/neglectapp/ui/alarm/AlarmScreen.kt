package com.example.neglectapp.ui.alarm

import android.app.Activity
import android.widget.Toast
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.neglectapp.domain.model.HeftosSession
import com.example.neglectapp.viewmodel.HeftosViewModel
import com.example.neglectapp.viewmodel.SessionViewModel
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DisplayAlarm(
    modifier: Modifier = Modifier,
    sessionViewModel: SessionViewModel = hiltViewModel()
) {
    var context = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    val viewModel: HeftosViewModel = viewModel()
    val intensity = viewModel.lightIntensity.collectAsState().value
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Black,
        targetValue = Color.White,
        animationSpec = infiniteRepeatable(
            animation = tween(1000 * intensity, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    var secondsToDisappear by remember { mutableStateOf(15) }

    LaunchedEffect(Unit) {
        while (secondsToDisappear > 0) {
            delay(1.seconds)
            secondsToDisappear -= 1
        }
        sessionViewModel.addSession(HeftosSession(0, LocalDateTime.now(), false, 0))
        activity?.finish()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (viewModel.light.collectAsState().value) {
                    color
                } else {
                    MaterialTheme.colors.background
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text("Heftos Alarm", modifier = Modifier.testTag("alarm"))
        Spacer(modifier = Modifier.height(50.dp))

        Text("Tijd resterend: $secondsToDisappear")
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = {
            Toast.makeText(context, "Activiteit opgenomen", Toast.LENGTH_LONG)
                .show();sessionViewModel.addSession(
            HeftosSession(
                0,
                LocalDateTime.now(),
                true,
                0
            )
        );activity?.finish()
        }, modifier = Modifier) {
            Icon(
                Icons.Default.Check,
                contentDescription = "Alarm accepteren",
                modifier = Modifier.size(width = 35.dp, height = 35.dp)
            )
        }
    }
}

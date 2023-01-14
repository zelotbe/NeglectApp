package com.example.neglectapp.ui.operatinghours

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Text
import com.example.neglectapp.navigation.Screen
import com.example.neglectapp.viewmodel.HeftosViewModel
import com.google.android.horologist.composables.TimePicker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime

@Composable
fun DisplayOperatingHours(
    navController: NavHostController,
    modifier: Modifier,
    operatingViewModel: OperatingViewModel = viewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val viewModel: HeftosViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (!operatingViewModel.showEnd.collectAsState().value) {
            Spacer(modifier = Modifier.height(10.dp))
            Text("Startuur")
            TimePicker(
                onTimeConfirm = {
                    viewModel.saveStartHour(it.toString()); Log.d(
                    "StartTime",
                    it.toString()
                ); operatingViewModel.toggleEnd()
                }, time = LocalTime.parse(
                    viewModel.startHour.collectAsState().value
                ), showSeconds = false
            )
        } else {
            Spacer(modifier = Modifier.height(10.dp))
            Text("Einduur")
            TimePicker(
                onTimeConfirm = {
                    viewModel.saveEndHour(it.toString()); Log.d(
                    "EndTime",
                    it.toString()
                ); Toast.makeText(context, "Werkingsuren opgeslagen", Toast.LENGTH_LONG)
                    .show(); scope.launch { delay(5); navController.navigate(Screen.Settings.route) {
                    popUpTo(Screen.Settings.route) {
                        inclusive = true
                    }
                } }
                },
                time = LocalTime.parse(viewModel.endHour.collectAsState().value),
                showSeconds = false
            )
        }
    }
}
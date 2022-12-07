package com.example.neglectapp.ui.operatinghours

import NeglectButton
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.neglectapp.data.datastore.StoreSessions
import com.example.neglectapp.data.datastore.StoreStimula
import com.example.neglectapp.navigation.Screen
import com.google.android.horologist.composables.TimePicker
import kotlinx.coroutines.launch
import java.time.LocalTime

@Composable
fun DisplayOperatingHours(
    navController: NavHostController,
    modifier: Modifier,
    operatingViewModel: OperatingViewModel = viewModel()
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val store = StoreSessions(context)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
    ) {
        if (!operatingViewModel.showEnd.collectAsState().value){
            Spacer(modifier = Modifier.height(10.dp))
            Text("Startuur")
            TimePicker( onTimeConfirm = {scope.launch { store.saveStart(it.toString()) }; Log.d("StartTime", it.toString()); operatingViewModel.toggleEnd()}, time = LocalTime.parse(store.getStart.collectAsState(initial = LocalTime.of(7,30).toString()).value), showSeconds = false)
        }else{
            Spacer(modifier = Modifier.height(10.dp))
            Text("Einduur")
            TimePicker(onTimeConfirm = {scope.launch { store.saveEnd(it.toString()) }; Log.d("EndTime", it.toString()); navController.navigate(Screen.Settings.route)}, time = LocalTime.parse(store.getEnd.collectAsState(initial = LocalTime.of(16,0).toString()).value), showSeconds = false )
        }
    }
}
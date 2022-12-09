package com.example.neglectapp.ui.session

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.neglectapp.data.datastore.StoreSessions
import com.example.neglectapp.ui.session.numberpicker.NumberStepper
import kotlinx.coroutines.launch

@Composable
fun DisplayNumberPicker(
    navController: NavHostController,
    modifier: Modifier,
    session: String,
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val store = StoreSessions(context)
    val min = store.getMinSession.collectAsState(initial = 1).value!!
    val max = store.getMaxSession.collectAsState(initial = 5).value!!

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (session){
            "min" -> {
                NumberStepper(displayValue = min, onValueChange = {scope.launch { store.saveMinSession(it) }} , session = session)
            }
            "max" -> {
                NumberStepper(displayValue = max, onValueChange = {scope.launch { store.saveMaxSession(it) }} , session = session)
            }
        }

    }
}
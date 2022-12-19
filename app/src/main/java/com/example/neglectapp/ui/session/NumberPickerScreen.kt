package com.example.neglectapp.ui.session

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.neglectapp.ui.session.numberpicker.NumberStepper
import com.example.neglectapp.viewmodel.HeftosViewModel

@Composable
fun DisplayNumberPicker(
    navController: NavHostController,
    modifier: Modifier,
    session: String,
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val viewModel: HeftosViewModel = viewModel()
    val min = viewModel.minSession.collectAsState().value
    val max = viewModel.maxSession.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (session){
            "min" -> {
                NumberStepper(displayValue = min, onValueChange = { viewModel.saveMinSession(it)} , session = session)
            }
            "max" -> {
                NumberStepper(displayValue = max, onValueChange = {viewModel.saveMaxSession(it)} , session = session)
            }
        }

    }
}
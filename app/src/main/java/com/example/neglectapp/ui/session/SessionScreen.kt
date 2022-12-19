package com.example.neglectapp.ui.session

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.neglectapp.navigation.Screen
import com.example.neglectapp.ui.session.numberpicker.NumberPicker
import com.example.neglectapp.viewmodel.HeftosViewModel

@Composable
fun DisplaySession(
    navController: NavHostController,
    modifier: Modifier,
){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel: HeftosViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        val items = (0..10).toList()
        val min = viewModel.minSession.collectAsState().value
        val max = viewModel.maxSession.collectAsState().value

        Spacer(modifier = Modifier.height(10.dp))
        Text("Sessies")
        Spacer(modifier = Modifier.height(5.dp))
        Text("Minimum")
        Spacer(modifier = Modifier.height(5.dp))
        NumberPicker(value = min, onClick = { navController.navigate(Screen.NumberPicker.route + "/min")})
        Spacer(modifier = Modifier.height(5.dp))
        Text("Maximum")
        Spacer(modifier = Modifier.height(5.dp))
        NumberPicker(value = max, onClick = { navController.navigate(Screen.NumberPicker.route + "/max")})

    }
}

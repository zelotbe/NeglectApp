package com.example.neglectapp.ui.session

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.neglectapp.ui.session.numberpicker.NumberPicker
import com.example.neglectapp.viewmodel.HeftosViewModel

@Composable
fun DisplaySession(
    navController: NavHostController,
    modifier: Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NumberPicker(navController = navController)
    }
}

package com.example.neglectapp.presentation.components.display

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CircularProgressIndicator

@Composable
fun DisplayProgress(){
    CircularProgressIndicator( progress = 0.4f,
        modifier = Modifier.fillMaxSize(),
        strokeWidth = 5.dp)
}
package com.example.neglectapp.presentation.ui.components.display

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.CircularProgressIndicator

@Composable
fun DisplayProgress(){
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
}
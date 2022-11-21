package com.example.neglectapp.presentation.ui.status

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import com.example.neglectapp.presentation.components.display.DisplayProgress

@Composable
fun DisplayStatus(
    status: Boolean = false,
    modifier: Modifier,
){
    if (status){
        Text("08:30 - 16:00")
        DisplayProgress()
    }else{
        Text("Geen actieve sessie")
    }
}
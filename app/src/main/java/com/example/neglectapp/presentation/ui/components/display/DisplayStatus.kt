package com.example.neglectapp.presentation.ui.components.display

import android.view.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text

@Composable
fun DisplayStatus(
    status: Boolean = true,
    modifier: Modifier,

){
    if (status){
        Text("08:30 - 16:00")
        DisplayProgress()
    }else{
        Text("Geen actieve sessie")
    }
}
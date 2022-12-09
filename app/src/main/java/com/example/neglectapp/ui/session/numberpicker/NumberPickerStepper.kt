package com.example.neglectapp.ui.session.numberpicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Stepper
import androidx.wear.compose.material.StepperDefaults
import androidx.wear.compose.material.Text

@Composable
fun NumberStepper(
    displayValue: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    session: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Stepper(
            value = displayValue,
            onValueChange = onValueChange,
            valueProgression = 1..10,
            increaseIcon = { Icon(StepperDefaults.Increase, "Increase") },
            decreaseIcon = { Icon(StepperDefaults.Decrease, "Decrease") }
        ) {
            Text("$session: $displayValue")
        }
    }
}
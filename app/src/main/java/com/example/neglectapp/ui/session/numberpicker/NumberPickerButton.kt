package com.example.neglectapp.ui.session.numberpicker

import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.*

//import com.example.neglectapp.ui.session.numberpicker.MinNumberPicker

@Composable
fun NumberPicker(
    value: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Text("$value",  modifier = Modifier
        .clickable { onClick() }
        .then(modifier))
}

package com.example.neglectapp.components.pieChart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text


@Composable
fun DisplayLegend(color: Color, legend: String) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.width(15.dp).height(3.dp).background(color)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = legend,
            color = Color.White
        )
    }
}
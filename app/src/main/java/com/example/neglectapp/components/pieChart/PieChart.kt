package com.example.neglectapp.components.pieChart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme

@Composable
fun PieChart(
    values: List<Int> = emptyList(),
    colors: List<Color> = listOf(MaterialTheme.colors.primaryVariant, MaterialTheme.colors.secondary),
    legend: List<String> = listOf("Wel", "Niet"),
    size: Dp = 150.dp
) {
    val total = values.sum()

    if(total != 0) {
        val proportions: List<Double> = values.map {
            it * 100.0 / total
        }

        val sweepAngles = proportions.map {
            360 * it / 100
        }

        Canvas(
            modifier = Modifier
                .size(size = size)
        ) {

            var startAngle = -90f

            for (i in sweepAngles.indices) {
                drawArc(
                    color = colors[i],
                    startAngle = startAngle,
                    sweepAngle = sweepAngles[i].toFloat(),
                    useCenter = true
                )
                startAngle += sweepAngles[i].toFloat()
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Column {
            for (i in values.indices) {
                DisplayLegend(color = colors[i], legend = legend[i])
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }
}
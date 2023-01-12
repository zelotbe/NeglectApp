package com.example.neglectapp.components.pieChart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme

@Composable
fun PieChart(
    values: List<Int> = emptyList(),
    colors: List<Color> = listOf(MaterialTheme.colors.primaryVariant, MaterialTheme.colors.secondary),
    legend: List<ImageVector> = listOf( Icons.Default.Check, Icons.Default.Close),
    size: Dp = 125.dp
) {
    val total = values.sum()

    if(total != 0) {
        val percentages: List<Double> = values.map {
            it * 100.0 / total
        }

        val sweepAngles = percentages.map {
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
                DisplayLegend(color = colors[i], legend = legend[i], percentage = percentages[i])
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}
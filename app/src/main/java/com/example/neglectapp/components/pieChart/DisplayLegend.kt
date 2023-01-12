package com.example.neglectapp.components.pieChart

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import java.text.DecimalFormat


@Composable
fun DisplayLegend(
    color: Color,
    legend: ImageVector,
    percentage: Double
) {
    val format = DecimalFormat("#.00")
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            legend,
            contentDescription = if(legend == Icons.Default.Check) "Interactie gehad" else "Geen interactie gehad",
            modifier = Modifier.size(width = 35.dp, height = 35.dp),
            tint = color
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = "${format.format(percentage)}%",
            color = Color.White
        )
    }
}
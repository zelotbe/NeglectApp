package com.example.neglectapp.components.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text

@Composable
fun SettingsItem(
    label: String,
    icon: ImageVector = Icons.Default.Warning,
){
    Box(
    ){
        Row() {
            Icon(
                icon,
                contentDescription = label,
                modifier = Modifier.size(width = 35.dp, height = 35.dp)
            )
            Text(label)
        }
    }
}
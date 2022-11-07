package com.example.neglectapp.presentation.ui.components.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon

@Composable
fun SettingsIcon(
    onClick: () -> Unit
) {
    Spacer(modifier = Modifier.height(15.dp))
    Box(
        modifier = Modifier,
    ){
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(25.dp).background(Color.Black.copy(alpha = 0f))
        ){
            Icon(
                Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier.size(width = 35.dp, height = 35.dp)
            )
        }
    }

}
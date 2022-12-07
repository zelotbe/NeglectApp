package com.example.neglectapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.MaterialTheme
import com.example.neglectapp.ui.theme.Typography
import com.example.neglectapp.ui.theme.wearColorPalette

@Composable
fun NeglectAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = wearColorPalette,
        typography = Typography,
        // For shapes, we generally recommend using the default Material Wear shapes which are
        // optimized for round and non-round devices.
        content = content
    )
}
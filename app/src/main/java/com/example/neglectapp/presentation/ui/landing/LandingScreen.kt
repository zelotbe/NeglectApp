package com.example.neglectapp.presentation.ui.landing

import NeglectButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.neglectapp.presentation.components.display.DisplayProgress
import com.example.neglectapp.presentation.components.settings.SettingsIcon
import com.example.neglectapp.presentation.navigation.Screen
import com.example.neglectapp.presentation.ui.status.DisplayStatus
import com.example.neglectapp.presentation.util.ButtonType

@Composable
fun DisplayLanding(
    navController: NavHostController,
    modifier: Modifier,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
//            verticalArrangement = Arrangement.Center
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
//                    contentAlignment = Alignment.Center
        ) {
//                    CircularProgressIndicator( progress = 0.4f,
//                        modifier = Modifier.fillMaxSize(),
//                        strokeWidth = 5.dp)
            DisplayProgress()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
//            verticalArrangement = Arrangement.Center
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SettingsIcon(navController = navController)
                Spacer(modifier = Modifier.height(50.dp))
                DisplayStatus(modifier = Modifier)
                Spacer(modifier = Modifier.height(15.dp))
                Column(modifier = Modifier.size(75.dp)) {
                    NeglectButton(
                        type = ButtonType.TEXT,
                        modifier = Modifier,
                        label = "Starten"
                    ) {}

                }

            }
        }
    }
}
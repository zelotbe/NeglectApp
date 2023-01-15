package com.example.neglectapp.ui.settings.stimulans

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.neglectapp.core.Constants.INTENSITY_CLICK
import com.example.neglectapp.navigation.Screen
import com.example.neglectapp.viewmodel.HeftosViewModel

@Composable
fun DisplayStimula(
    status: Boolean = false,
    modifier: Modifier,
    navController: NavHostController,
//    settingsViewModel: SettingsViewModel = viewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    //STIMULA DATA STORE
    val viewModel: HeftosViewModel = viewModel()
    val getVibration = viewModel.vibration.collectAsState().value
    val getSound = viewModel.sound.collectAsState().value
    val getLight = viewModel.light.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScalingLazyColumn(
            horizontalAlignment = Alignment.Start,
        ) {
            item {
                SplitToggleChip(
                    checked = getVibration,
                    onCheckedChange = {},
                    onClick = { navController.navigate(Screen.Intensity.route + "/Vibratie") },
                    toggleControl = {
                        Switch(
                            checked = getVibration,
                            onCheckedChange = { viewModel.saveVibration(getVibration.not()) }
                        )
                    },
                    label = {
                        Text("Vibratie")
                    },
                    secondaryLabel = {
                        if (getVibration) {
                            Text(INTENSITY_CLICK)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = getVibration
                )
            }
            item {
                SplitToggleChip(
                    checked = getSound,
                    onCheckedChange = {},
                    onClick = { navController.navigate(Screen.Intensity.route + "/Geluid") },
                    toggleControl = {
                        Switch(
                            checked = getSound,
                            onCheckedChange = { viewModel.saveSound(getSound.not()) }
                        )
                    },
                    label = {
                        Text("Geluid")
                    },
                    secondaryLabel = {
                        if (getSound) {
                            Text(INTENSITY_CLICK)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = getSound
                )
            }
            item {
                SplitToggleChip(
                    checked = getLight,
                    onCheckedChange = {},
                    onClick = { navController.navigate(Screen.Intensity.route + "/Licht") },
                    toggleControl = {
                        Switch(
                            checked = getLight,
                            onCheckedChange = { viewModel.saveLight(getLight.not()) }
                        )
                    },
                    label = {
                        Text("Licht")
                    },
                    secondaryLabel = {
                        if (getLight) {
                            Text(INTENSITY_CLICK)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = getLight
                )
            }
        }
    }
}
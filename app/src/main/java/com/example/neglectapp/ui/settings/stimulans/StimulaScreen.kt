package com.example.neglectapp.ui.settings.stimulans

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.neglectapp.data.datastore.StoreStimula
import com.example.neglectapp.navigation.Screen
//import com.example.neglectapp.ui.settings.SettingsViewModel
import kotlinx.coroutines.launch

@Composable
fun DisplayStimula(
    status: Boolean = false,
    modifier: Modifier,
    navController: NavHostController,
//    settingsViewModel: SettingsViewModel = viewModel()
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    //STIMULA DATA STORE
    val stimulaStore = StoreStimula(context)
    val getVibration = stimulaStore.getVibration.collectAsState(initial = false)
    val getSound = stimulaStore.getSound.collectAsState(initial = false)
    val getLight = stimulaStore.getLight.collectAsState(initial = false)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScalingLazyColumn(
            horizontalAlignment = Alignment.Start,
        ){
            item() {
                SplitToggleChip(
                    checked = getVibration.value!!,
                    onCheckedChange = {},
                    onClick = { navController.navigate(Screen.Intensity.route + "/Vibratie") },
                    toggleControl = {
                        Switch(
                            checked = getVibration.value!!,
                            onCheckedChange = { scope.launch { stimulaStore.saveVibration(getVibration.value!!.not())  } }
                        )
                    },
                    label = {
                        Text("Vibratie")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item() {
                SplitToggleChip(
                    checked = getSound.value!!,
                    onCheckedChange = {},
                    onClick = { navController.navigate(Screen.Intensity.route + "/Geluid") },
                    toggleControl = {
                        Switch(
                            checked = getSound.value!!,
                            onCheckedChange = { scope.launch { stimulaStore.saveSound(getSound.value!!.not())  } }
                        )
                    },
                    label = {
                        Text("Geluid")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item(){
                SplitToggleChip(
                    checked = getLight.value!!,
                    onCheckedChange = {},
                    onClick = { navController.navigate(Screen.Intensity.route + "/Licht") },
                    toggleControl = {
                        Switch(
                            checked = getLight.value!!,
                            onCheckedChange = { scope.launch { stimulaStore.saveLight(getLight.value!!.not())  } }
                        )
                    },
                    label = {
                        Text("Licht")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
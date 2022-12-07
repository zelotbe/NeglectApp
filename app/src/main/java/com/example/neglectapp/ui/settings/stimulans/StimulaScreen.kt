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
import com.example.neglectapp.ui.settings.SettingsViewModel
import kotlinx.coroutines.launch

@Composable
fun DisplayStimula(
    status: Boolean = false,
    modifier: Modifier,
    navController: NavHostController,
    settingsViewModel: SettingsViewModel = viewModel()
){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    //VIBRATION DATA STORE
    val stimulaStore = StoreStimula(context)
    val getVibration = stimulaStore.getVibration.collectAsState(initial = false)
    val getSound = stimulaStore.getSound.collectAsState(initial = false)
    val getLight = stimulaStore.getLight.collectAsState(initial = false)

    //SOUND DATA STORE
//    val soundStore = StoreSound(context)
//    val getSound = soundStore.getSound.collectAsState(initial = false)
//    //LIGHT DATA STORE
//    val lightStore = StoreLight(context)
//    val getLight = lightStore.getLight.collectAsState(initial = false)
//    val vibrationChecked by settingsViewModel.vibrate.collectAsState()
//    val soundChecked by settingsViewModel.sound.collectAsState()
//    val lightChecked by settingsViewModel.light.collectAsState()
//    val settings = context.dataStore.data.collectAsState(
//        initial = AppSettings()
//    ).value

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScalingLazyColumn(
            horizontalAlignment = Alignment.Start,
        ){
//            items(settings.stimula){ setting ->
//                SplitToggleChip(
//                    checked = setting.active,
//                    onCheckedChange = {},
//                    onClick = { navController.navigate(Screen.Intensity.route) },
//                    toggleControl = {
//                        Switch(
//                            checked = setting.active,
//                            onCheckedChange = {scope.launch { setActiveStimula(context, setting); Log.d("Stimula:",
//                                setting.toString()
//                            ) }}
//                        )
//                    },
//                    label = {
//                        Text("" + setting.title)
//                    },
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
            item() {
                SplitToggleChip(
                    checked = getVibration.value!!,
                    onCheckedChange = {},
                    onClick = { navController.navigate(Screen.Intensity.route) },
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
                    onClick = {  },
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
                    onClick = {  },
                    toggleControl = {
                        Switch(
                            checked = getLight.value!!,
                            onCheckedChange = { scope.launch { stimulaStore.saveSound(getLight.value!!.not())  } }
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

//private suspend fun setActiveStimula(context: Context, stimula: StimulaObject){
//    context.dataStore.updateData {
//        it.copy(
//            stimula = it.stimula.mutate {
////                it.onEach{ e -> if(e.title == stimula.title){ e.active = stimula.active.not()} }
////            { stimulaObject ->
//                for (stimulaObject in it) {
//                    Log.e("STIMULA:", stimulaObject.toString())
//                    if (stimulaObject.title == stimula.title) {
//                        Log.e("TAG", "${stimula.active}")
//                        stimulaObject.active = true
//                    }
//                }
//            }
////                it.add(StimulaObject(title = "Test", active = true, intensity = 1.0))
////            }
//        )
//    }
//}

//private suspend fun getStimula(context: Context){
//    context.dataStore.data.collectAsState()
//}
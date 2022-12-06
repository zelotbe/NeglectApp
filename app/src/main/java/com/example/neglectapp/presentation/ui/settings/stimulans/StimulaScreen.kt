package com.example.neglectapp.presentation.ui.settings.stimulans

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.neglectapp.presentation.data.AppSettings
import com.example.neglectapp.presentation.data.StimulaObject
import com.example.neglectapp.presentation.dataStore
import com.example.neglectapp.presentation.navigation.Screen
import com.example.neglectapp.presentation.ui.settings.SettingsViewModel
import kotlinx.collections.immutable.mutate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.flow.toList
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
    val vibrationChecked by settingsViewModel.vibrate.collectAsState()
    val soundChecked by settingsViewModel.sound.collectAsState()
    val lightChecked by settingsViewModel.light.collectAsState()
    val settings = context.dataStore.data.collectAsState(
        initial = AppSettings()
    ).value

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScalingLazyColumn(
            horizontalAlignment = Alignment.Start,
        ){
            items(settings.stimula){ setting ->
                SplitToggleChip(
                    checked = setting.active,
                    onCheckedChange = {},
                    onClick = { navController.navigate(Screen.Intensity.route) },
                    toggleControl = {
                        Switch(
                            checked = setting.active,
                            onCheckedChange = {scope.launch { setActiveStimula(context, setting); Log.d("Stimula:",
                                setting.toString()
                            ) }}
                        )
                    },
                    label = {
                        Text("" + setting.title)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
//            item() {
//                SplitToggleChip(
//                    checked = soundChecked,
//                    onCheckedChange = {},
//                    onClick = {  },
//                    toggleControl = {
//                        Switch(
//                            checked = soundChecked,
//                            onCheckedChange = { settingsViewModel.toggleSound() }
//                        )
//                    },
//                    label = {
//                        Text("Geluid")
//                    },
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//            item(){
//                SplitToggleChip(
//                    checked = lightChecked,
//                    onCheckedChange = {},
//                    onClick = {  },
//                    toggleControl = {
//                        Switch(
//                            checked = lightChecked,
//                            onCheckedChange = { settingsViewModel.toggleLight() }
//                        )
//                    },
//                    label = {
//                        Text("value:" + lightChecked)
//                    },
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
        }
    }
}

private suspend fun setActiveStimula(context: Context, stimula: StimulaObject){

    context.dataStore.updateData {
        it.copy(
            stimula = it.stimula.mutate {
//                for (stimulaObject in it) {
//                    if (stimulaObject.title == stimula.title){
//                        Log.e("TAG", "${stimula.active}")
//
//                        stimulaObject.active = stimula.active.not()
//                    }
//                }
                it.add(StimulaObject(title = "Test", active = true, intensity = 1.0))
            }
        )
    }
}

//private suspend fun getStimula(context: Context){
//    context.dataStore.data.collectAsState()
//}
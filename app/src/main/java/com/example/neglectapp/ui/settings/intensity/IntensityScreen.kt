package com.example.neglectapp.ui.settings.intensity

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.InlineSlider
import androidx.wear.compose.material.InlineSliderDefaults
import androidx.wear.compose.material.Text
import com.example.neglectapp.data.datastore.StoreStimula
import kotlinx.coroutines.launch

@Composable
fun DisplayIntensity(
    modifier: Modifier,
    stimula: String,
    ){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    //VIBRATION DATA STORE
    val stimulaStore = StoreStimula(context)
    val getVibration = stimulaStore.getVibrationIntensity.collectAsState(initial = 1)
    val getSound = stimulaStore.getSoundIntensity.collectAsState(initial = 0)
    val getLight = stimulaStore.getLightIntensity.collectAsState(initial = 0)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (stimula){
            "Vibratie" -> {
                Text("$stimula Intensiteit")
                InlineSlider(
                    value = getVibration.value!!,
                    onValueChange = { Log.d("INTENSITY", "${getVibration.value!!}" );scope.launch { stimulaStore.saveVibrationIntensity(it)  } },
                    valueProgression = 1..10,
                    increaseIcon = { Icon(InlineSliderDefaults.Increase, "Increase") },
                    decreaseIcon = { Icon(InlineSliderDefaults.Decrease, "Decrease") }
                )
            }
            "Geluid" -> {
                Text("$stimula Intensiteit")
                InlineSlider(
                    value = getSound.value!!,
                    onValueChange = { Log.d("INTENSITY", "${getSound.value!!}" );scope.launch { stimulaStore.saveSoundIntensity(it)  } },
                    valueProgression = 1..10,
                    increaseIcon = { Icon(InlineSliderDefaults.Increase, "Increase") },
                    decreaseIcon = { Icon(InlineSliderDefaults.Decrease, "Decrease") }
                )
            }
            "Licht" -> {
                Text("$stimula Intensiteit")
                InlineSlider(
                    value = getLight.value!!,
                    onValueChange = { Log.d("INTENSITY", "${getLight.value!!}" );scope.launch { stimulaStore.saveLightIntensity(it)  } },
                    valueProgression = 1..10,
                    increaseIcon = { Icon(InlineSliderDefaults.Increase, "Increase") },
                    decreaseIcon = { Icon(InlineSliderDefaults.Decrease, "Decrease") }
                )
            }
        }

    }
}
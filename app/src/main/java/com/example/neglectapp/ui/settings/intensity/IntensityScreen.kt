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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.InlineSlider
import androidx.wear.compose.material.InlineSliderDefaults
import androidx.wear.compose.material.Text
import com.example.neglectapp.viewmodel.HeftosViewModel

@Composable
fun DisplayIntensity(
    modifier: Modifier,
    stimula: String,
    ){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    //VIBRATION DATA STORE
    val viewModel: HeftosViewModel = viewModel()
    val getVibration = viewModel.vibrationIntensity.collectAsState().value
    val getSound = viewModel.soundIntensity.collectAsState().value
    val getLight = viewModel.lightIntensity.collectAsState().value

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
                    value = getVibration,
                    onValueChange = { Log.d("INTENSITY", "$getVibration" ); viewModel.saveVibrationIntensity(it) },
                    valueProgression = 1..10,
                    increaseIcon = { Icon(InlineSliderDefaults.Increase, "Increase") },
                    decreaseIcon = { Icon(InlineSliderDefaults.Decrease, "Decrease") }
                )
            }
            "Geluid" -> {
                Text("$stimula Intensiteit")
                InlineSlider(
                    value = getSound,
                    onValueChange = { Log.d("INTENSITY", "$getSound" );viewModel.saveSoundIntensity(it) },
                    valueProgression = 1..10,
                    increaseIcon = { Icon(InlineSliderDefaults.Increase, "Increase") },
                    decreaseIcon = { Icon(InlineSliderDefaults.Decrease, "Decrease") }
                )
            }
            "Licht" -> {
                Text("$stimula Intensiteit")
                InlineSlider(
                    value = getLight,
                    onValueChange = { Log.d("INTENSITY", "$getLight" );viewModel.saveLightIntensity(it) },
                    valueProgression = 1..10,
                    increaseIcon = { Icon(InlineSliderDefaults.Increase, "Increase") },
                    decreaseIcon = { Icon(InlineSliderDefaults.Decrease, "Decrease") }
                )
            }
        }

    }
}
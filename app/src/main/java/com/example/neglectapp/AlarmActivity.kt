package com.example.neglectapp

import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.neglectapp.ui.alarm.DisplayAlarm
import com.example.neglectapp.viewmodel.HeftosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlarmActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    private lateinit var r: Ringtone
    private lateinit var vibrator: Vibrator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val measure = MeasureClient
            val viewModel: HeftosViewModel = viewModel()
            val context = LocalContext.current
            var alarmUri: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

            if (alarmUri == null) {
                alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            }

            r = RingtoneManager.getRingtone(context, alarmUri)
            vibrator = context.getSystemService(VIBRATOR_SERVICE) as Vibrator

            val sound = viewModel.sound.collectAsState().value
            val soundIntensity = viewModel.soundIntensity.collectAsState().value

            if (sound) {
                r.volume = 1f / soundIntensity
                r.isLooping = true
                r.play()
            }

            val vibration = viewModel.vibration.collectAsState().value
            val vibrationIntensity = viewModel.vibrationIntensity.collectAsState().value
            if (vibration) {
                val strength = 255 / vibrationIntensity
                val pattern = longArrayOf(
                    0,
                    500,
                    100,
                    500,
                    100,
                    500,
                    100,
                    500,
                    100,
                    500,
                    100,
                    500,
                    100,
                    500,
                    100,
                    500,
                    100,
                    500
                )
                val amplitude = intArrayOf(
                    0,
                    strength,
                    0,
                    strength,
                    0,
                    strength,
                    0,
                    strength,
                    0,
                    strength,
                    0,
                    strength,
                    0,
                    strength,
                    0,
                    strength,
                    0,
                    strength
                )
                val vibrationEffect: VibrationEffect =
                    VibrationEffect.createWaveform(pattern, amplitude, 1)
                // it is safe to cancel other
                // vibrations currently taking place
                vibrator.cancel()
                vibrator.vibrate(vibrationEffect)
            }
            DisplayAlarm()

        }
    }

    override fun onStop() {
        super.onStop()
        r.stop()
        vibrator.cancel()
    }

}
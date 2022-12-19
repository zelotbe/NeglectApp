package com.example.neglectapp

import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Text
import com.example.neglectapp.ui.alarm.DisplayAlarm
import com.example.neglectapp.viewmodel.HeftosViewModel

class AlarmActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)

    private lateinit var r:Ringtone
    private lateinit var vibrator:Vibrator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: HeftosViewModel = viewModel()
            var context = LocalContext.current
            var alarmUri: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

            if (alarmUri == null) {
                alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            }

            r = RingtoneManager.getRingtone(context, alarmUri)
            vibrator =
                context.getSystemService(VIBRATOR_SERVICE) as Vibrator
            val vibrationEffect1: VibrationEffect =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    VibrationEffect.createOneShot(100000, VibrationEffect.DEFAULT_AMPLITUDE)
                } else {
                    Log.e("TAG", "Cannot vibrate device..")
                    TODO("VERSION.SDK_INT < O")
                }
            if (viewModel.sound.collectAsState().value) {
                r.play()
                Text("Sound is active")
            }
            if (viewModel.vibration.collectAsState().value) {
                // it is safe to cancel other
                // vibrations currently taking place
                vibrator.cancel()
                vibrator.vibrate(vibrationEffect1)
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
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
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.neglectapp.data.datastore.StoreSessions
import com.example.neglectapp.data.datastore.StoreStimula
import com.example.neglectapp.ui.alarm.DisplayAlarm
import com.example.neglectapp.ui.theme.wearColorPalette

class AlarmActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    private lateinit var r:Ringtone
    private lateinit var vibrator:Vibrator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var context = LocalContext.current
            val stimulaStore = StoreStimula(context)
            val getVibration by stimulaStore.getVibration.collectAsState(initial = false)
            val getSound by stimulaStore.getSound.collectAsState(initial = false)
            val sessionStore = StoreSessions(context)
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
            if (getSound == true) {
                r.play()
                Text("Sound is active")
            }
            if (getVibration == true) {
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
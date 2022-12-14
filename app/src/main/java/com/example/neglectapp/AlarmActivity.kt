package com.example.neglectapp

import android.Manifest
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.neglectapp.ui.alarm.DisplayAlarm

class AlarmActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                DisplayAlarm()
            }
    }

}
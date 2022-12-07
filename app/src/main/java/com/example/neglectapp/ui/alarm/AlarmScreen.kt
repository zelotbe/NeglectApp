package com.example.neglectapp.ui.alarm

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.wear.compose.material.MaterialTheme

@Composable
fun DisplayAlarm(
    navController: NavHostController,
    modifier: Modifier,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
        ) {
            val vibrator = LocalContext.current.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            val vibrationEffect1: VibrationEffect =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    VibrationEffect.createOneShot(100000, VibrationEffect.DEFAULT_AMPLITUDE)
                } else {
                    Log.e("TAG", "Cannot vibrate device..")
                    TODO("VERSION.SDK_INT < O")
                }

            // it is safe to cancel other
            // vibrations currently taking place
            vibrator.cancel()
            vibrator.vibrate(vibrationEffect1)
        }
    }
}
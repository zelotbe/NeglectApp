package com.example.neglectapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController

class MainActivity : ComponentActivity() {
    internal lateinit var navController: NavHostController
    @OptIn(ExperimentalWearMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberSwipeDismissableNavController()
            WearApp(
                modifier = Modifier,
                swipeDismissableNavController = navController,
            )
        }
    }
}

package com.example.neglectapp.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.datastore.dataStore
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.neglectapp.presentation.data.AppSettings
import com.example.neglectapp.presentation.data.AppSettingsSerializer
import com.example.neglectapp.presentation.data.StimulaObject
import kotlinx.collections.immutable.mutate

val Context.dataStore by dataStore("settings.json", AppSettingsSerializer)

class MainActivity : ComponentActivity() {
    internal lateinit var navController: NavHostController
    @OptIn(ExperimentalWearMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberSwipeDismissableNavController()
            val appSettings = dataStore.data.collectAsState(
                initial = AppSettings()
            ).value
            val scope = rememberCoroutineScope()

            WearApp(
                modifier = Modifier,
                swipeDismissableNavController = navController,
            )
        }
    }
}

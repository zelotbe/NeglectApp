package com.example.neglectapp.presentation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.currentBackStackEntryAsState
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.neglectapp.presentation.data.AppSettings
import com.example.neglectapp.presentation.navigation.DestinationScrollType
import com.example.neglectapp.presentation.navigation.SCROLL_TYPE_NAV_ARGUMENT
import com.example.neglectapp.presentation.navigation.Screen
import com.example.neglectapp.presentation.ui.ScalingLazyListStateViewModel
import com.example.neglectapp.presentation.ui.ScrollStateViewModel
import com.example.neglectapp.presentation.ui.alarm.DisplayAlarm
import com.example.neglectapp.presentation.ui.landing.DisplayLanding
import com.example.neglectapp.presentation.ui.settings.DisplaySettings
import com.example.neglectapp.presentation.ui.settings.intensity.DisplayIntensity
import com.example.neglectapp.presentation.ui.settings.stimulans.DisplayStimula


@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun WearApp(
    modifier: Modifier,
    swipeDismissableNavController: NavHostController = rememberSwipeDismissableNavController(),
) {
    MaterialTheme {
        // Mobile guidelines specify that if you back navigate out of a screen and then
        // later navigate into it again, it should be in its initial scroll state (not the last
        // scroll location it was in before you backed out).
        val currentBackStackEntry by swipeDismissableNavController.currentBackStackEntryAsState()
        val scrollType =
            currentBackStackEntry?.arguments?.getSerializable(SCROLL_TYPE_NAV_ARGUMENT)
                ?: DestinationScrollType.NONE

        Scaffold(
            modifier = modifier,
            positionIndicator = {
                // Only displays the position indicator for scrollable content.
                when (scrollType) {
                    DestinationScrollType.SCALING_LAZY_COLUMN_SCROLLING -> {
                        // Get or create the ViewModel associated with the current back stack entry
                        val scrollViewModel: ScalingLazyListStateViewModel =
                            viewModel(currentBackStackEntry!!)
                        PositionIndicator(scalingLazyListState = scrollViewModel.scrollState)
                    }
                    DestinationScrollType.COLUMN_SCROLLING -> {
                        // Get or create the ViewModel associated with the current back stack entry
                        val viewModel: ScrollStateViewModel = viewModel(currentBackStackEntry!!)
                        PositionIndicator(scrollState = viewModel.scrollState)
                    }
                }

            }
        ) {
            SwipeDismissableNavHost(
                navController = swipeDismissableNavController,
                startDestination = Screen.Landing.route,
                modifier = Modifier.background(MaterialTheme.colors.background)
            ) {
                // LANDING PAGE
                composable(
                    route = Screen.Landing.route
                ){
                    DisplayLanding(navController = swipeDismissableNavController, modifier = Modifier )
                }
                // SETTINGS PAGE
                composable(
                    route = Screen.Settings.route
                ){
                    DisplaySettings(modifier = Modifier, navController = swipeDismissableNavController)
                }
                // STIMULA SETTINGS
                composable(
                    route = Screen.Stimula.route
                ){
                    DisplayStimula(modifier = Modifier, navController = swipeDismissableNavController)
                }
                // INTENSITY - STIMULA
                composable(
                    route = Screen.Intensity.route
                ){
                    DisplayIntensity(modifier = Modifier)
                }
                // ALARM SCREEN
                composable(
                    route = Screen.Alarm.route
                ){
                    DisplayAlarm(modifier = Modifier, navController = swipeDismissableNavController)
                }
            }
        }
    }
}

@Preview(name= "Preview", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp(Modifier)
}
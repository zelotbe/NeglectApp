package com.example.neglectapp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.material.*
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.currentBackStackEntryAsState
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.neglectapp.navigation.DestinationScrollType
import com.example.neglectapp.navigation.SCROLL_TYPE_NAV_ARGUMENT
import com.example.neglectapp.navigation.Screen
import com.example.neglectapp.navigation.WATCH_ID_NAV_ARGUMENT
import com.example.neglectapp.ui.ScalingLazyListStateViewModel
import com.example.neglectapp.ui.ScrollStateViewModel

import com.example.neglectapp.ui.landing.DisplayLanding
import com.example.neglectapp.ui.operatinghours.DisplayOperatingHours
import com.example.neglectapp.ui.session.DisplayNumberPicker
import com.example.neglectapp.ui.session.DisplaySession
import com.example.neglectapp.ui.settings.DisplaySettings
import com.example.neglectapp.ui.settings.intensity.DisplayIntensity
import com.example.neglectapp.ui.settings.stimulans.DisplayStimula
import com.example.neglectapp.service.SessionService
import com.example.neglectapp.ui.piechart.DisplayPieChart


@OptIn(ExperimentalWearMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun WearApp(
    modifier: Modifier,
    swipeDismissableNavController: NavHostController = rememberSwipeDismissableNavController(),
    sessionService: SessionService,
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
                    DisplayLanding(navController = swipeDismissableNavController, modifier = Modifier, sessionService = sessionService )
                }
                // SETTINGS PAGE
                composable(
                    route = Screen.Settings.route,
                    arguments = listOf(
                        navArgument(SCROLL_TYPE_NAV_ARGUMENT) {
                            type = NavType.EnumType(DestinationScrollType::class.java)
                            defaultValue = DestinationScrollType.SCALING_LAZY_COLUMN_SCROLLING
                        }
                    )
                ){
                    val scalingLazyListState = scalingLazyListState(it)
                    DisplaySettings(modifier = Modifier, navController = swipeDismissableNavController, scalingLazyListState = scalingLazyListState)
                }
                // STIMULA SETTINGS
                composable(
                    route = Screen.Stimula.route
                ){
                    DisplayStimula(modifier = Modifier, navController = swipeDismissableNavController)
                }
                // INTENSITY - STIMULA
                composable(
                    route = Screen.Intensity.route + "/{stimula}",
                    arguments = listOf(navArgument("stimula"){ type = NavType.StringType})
                ){ backStackEntry ->
                    DisplayIntensity(modifier = Modifier, backStackEntry.arguments?.getString("stimula")!!)
                }
                // ALARM SCREEN
                composable(
                    route = Screen.Alarm.route
                ){
                    AlarmActivity()
                }
                // OPERATING HOURS SCREEN
                composable(
                    route = Screen.OperatingHours.route
                ){
                    DisplayOperatingHours(modifier = Modifier, navController = swipeDismissableNavController)
                }
                // SESSION SCREEN
                composable(
                    route = Screen.Session.route
                ){
                    DisplaySession(modifier = Modifier, navController = swipeDismissableNavController)
                }
                //SESSION NUMBER PICKER SCREEN
                composable(
                    route = Screen.NumberPicker.route + "/{session}",
                    arguments = listOf(navArgument("session"){ type = NavType.StringType})
                ){ backStackEntry ->
                    DisplayNumberPicker(navController = swipeDismissableNavController, modifier = Modifier, backStackEntry.arguments?.getString("session")!!)
                }
                //DATA CHART SCREEN
                composable(
                    route = Screen.PieChart.route,
                    arguments = listOf(
                        navArgument(SCROLL_TYPE_NAV_ARGUMENT) {
                            type = NavType.EnumType(DestinationScrollType::class.java)
                            defaultValue = DestinationScrollType.COLUMN_SCROLLING
                        }
                    )
                ){
                    val scrollState = scrollState(it)
                    DisplayPieChart(navController = swipeDismissableNavController, modifier = Modifier, scrollState = scrollState)
                }
            }
        }
    }
}

@Composable
private fun scrollState(it: NavBackStackEntry): ScrollState {
    val passedScrollType = it.arguments?.getSerializable(SCROLL_TYPE_NAV_ARGUMENT)

    check(passedScrollType == DestinationScrollType.COLUMN_SCROLLING) {
        "Scroll type must be DestinationScrollType.COLUMN_SCROLLING"
    }

    val scrollViewModel: ScrollStateViewModel = viewModel(it)
    return scrollViewModel.scrollState
}

@Composable
private fun scalingLazyListState(it: NavBackStackEntry): ScalingLazyListState {
    val passedScrollType = it.arguments?.getSerializable(SCROLL_TYPE_NAV_ARGUMENT)

    check(
        passedScrollType == DestinationScrollType.SCALING_LAZY_COLUMN_SCROLLING
    ) {
        "Scroll type must be DestinationScrollType.SCALING_LAZY_COLUMN_SCROLLING"
    }

    val scrollViewModel: ScalingLazyListStateViewModel = viewModel(it)

    return scrollViewModel.scrollState
}

//@Preview(name= "Preview", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
//@Composable
//fun DefaultPreview() {
//    WearApp(Modifier, ForegroundStimulaService)
//}
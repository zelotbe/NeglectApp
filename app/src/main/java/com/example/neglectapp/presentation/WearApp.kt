package com.example.neglectapp.presentation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.currentBackStackEntryAsState
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.neglectapp.presentation.data.States
import com.example.neglectapp.presentation.navigation.DestinationScrollType
import com.example.neglectapp.presentation.navigation.SCROLL_TYPE_NAV_ARGUMENT
import com.example.neglectapp.presentation.navigation.Screen
import com.example.neglectapp.presentation.ui.ScalingLazyListStateViewModel
import com.example.neglectapp.presentation.ui.ScrollStateViewModel
import com.example.neglectapp.presentation.ui.landing.DisplayLanding
import com.example.neglectapp.presentation.ui.settings.DisplaySettings
import com.example.neglectapp.presentation.ui.settings.stimulans.DisplayStimula
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private val _uiState = MutableStateFlow(States())
val uiState: StateFlow<States> = _uiState.asStateFlow()

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun WearApp(
    modifier: Modifier,
    swipeDismissableNavController: NavHostController = rememberSwipeDismissableNavController()
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
            }
        }
    }
}

@Preview(name= "Light Mode", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode",
//    device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true
//)
@Composable
fun DefaultPreview() {
    WearApp(Modifier)
}
//@Composable
//fun WearApp() {
//    MaterialTheme {
//        val currentBackStackEntry by swipeDismissableNavController.currentBackStackEntryAsState()
//        /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
//         * version of LazyColumn for wear devices with some added features. For more information,
//         * see d.android.com/wear/compose.
//         */
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(MaterialTheme.colors.background),
////            verticalArrangement = Arrangement.Center
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            SettingsIcon(onClick = { /*TODO*/ })
//            Spacer(modifier = Modifier.height(45.dp))
//            DisplayStatus(modifier = Modifier)
//            Spacer(modifier = Modifier.height(25.dp))
//            Column(modifier = Modifier.size(75.dp)) {
//                NeglectButton(type = ButtonType.TEXT, modifier = Modifier, label = "Starten"){}
//
//            }
//
//        }
//    }
//}

package com.example.neglectapp.presentation.navigation

// Used as a Navigation Argument for the WatchDetail Screen.
const val WATCH_ID_NAV_ARGUMENT = "watchId"

// Navigation Argument for Screens with scrollable types:
// 1. WatchList -> ScalingLazyColumn
// 2. WatchDetail -> Column (with scaling enabled)
const val SCROLL_TYPE_NAV_ARGUMENT = "scrollType"

sealed class Screen(val route: String){
    object StatusScreen: Screen("status")
    object SettingsScreen: Screen("settings")
    object StimulansScreen: Screen("stimulans")
    object IntensityScreen: Screen("intensity")
    object OperatingHoursScreen: Screen("operatinghours")
    object SessionScreen: Screen("session")
}

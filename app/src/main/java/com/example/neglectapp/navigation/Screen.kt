package com.example.neglectapp.navigation

// Used as a Navigation Argument for the WatchDetail Screen.
const val WATCH_ID_NAV_ARGUMENT = "watchId"

// Navigation Argument for Screens with scrollable types:
// 1. WatchList -> ScalingLazyColumn
// 2. WatchDetail -> Column (with scaling enabled)
const val SCROLL_TYPE_NAV_ARGUMENT = "scrollType"

sealed class Screen(val route: String){
    object Landing: Screen("landing")
    object Status: Screen("status")
    object Settings: Screen("settings")
    object Stimula: Screen("stimula")
    object Intensity: Screen("intensity")
    object OperatingHours: Screen("operatinghours")
    object Session: Screen("session")
    object Alarm: Screen("alarm")
    object NumberPicker: Screen("numberpicker")
    object PieChart: Screen("chart")



}

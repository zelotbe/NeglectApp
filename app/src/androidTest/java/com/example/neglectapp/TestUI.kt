package com.example.neglectapp

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.TextLayoutResult
import androidx.navigation.NavHostController
import androidx.test.core.app.ActivityScenario
import androidx.wear.compose.material.MaterialTheme
import com.example.neglectapp.service.SessionState
import com.example.neglectapp.ui.operatinghours.DisplayOperatingHours
import com.example.neglectapp.ui.status.DisplayStatus
import org.junit.Rule
import org.junit.Test

class TestUI {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testStatusScreenStarted() {
        composeTestRule.setContent {
            DisplayStatus(modifier = Modifier, status = SessionState.Started)
        }
        composeTestRule
            .onNodeWithTag("operatinghours")
            .assertExists()
        composeTestRule
            .onNodeWithTag("goodbye")
            .assertDoesNotExist()
    }

    @Test
    fun testStatusScreenClosed() {
        composeTestRule.setContent {
            DisplayStatus(modifier = Modifier, status = SessionState.ClosedOperatingHours)
        }
        composeTestRule
            .onNodeWithTag("operatinghours")
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithTag("goodbye")
            .assertExists()
    }
    @Test
    fun testAlarmScreen(){
        val alarmScreen = ActivityScenario.launch(AlarmActivity::class.java)
        composeTestRule.onNodeWithTag("alarm")
            .assertIsDisplayed()
    }

}
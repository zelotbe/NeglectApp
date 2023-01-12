package com.example.neglectapp.ui.piechart

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Text
import com.example.neglectapp.components.pieChart.PieChart
import com.example.neglectapp.viewmodel.SessionViewModel

@Composable
fun DisplayPieChart(
    navController: NavHostController,
    modifier: Modifier,
    scrollState: ScrollState,
    viewModel: SessionViewModel = hiltViewModel()
){
    val amountInteracted = viewModel.amountInteracted.collectAsState(initial = 0)
    val amountNotInteracted = viewModel.amountNotInteracted.collectAsState(initial = 0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(scrollState, Orientation.Vertical)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text("Data")
        Text("Interactie")
        Spacer(modifier = Modifier.height(10.dp))
        PieChart(values = listOf(amountInteracted.value, amountNotInteracted.value))
    }
}
package com.example.neglectapp.ui.piechart

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.neglectapp.components.pieChart.PieChart
import com.example.neglectapp.viewmodel.SessionViewModel

@Composable
fun DisplayPieChart(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: SessionViewModel = hiltViewModel()
){
    val amountInteracted = viewModel.amountInteracted.collectAsState(initial = 0)
    val amountNotInteracted = viewModel.amountNotInteracted.collectAsState(initial = 0)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PieChart(values = listOf(amountInteracted.value, amountNotInteracted.value))
    }
}
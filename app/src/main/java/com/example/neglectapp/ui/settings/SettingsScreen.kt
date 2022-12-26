package com.example.neglectapp.ui.settings


import androidx.compose.foundation.layout.*

import androidx.wear.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.example.neglectapp.R
import com.example.neglectapp.navigation.Screen

@Composable
fun DisplaySettings(
    modifier: Modifier,
    navController: NavHostController,
//    settingsViewModel: SettingsViewModel = viewModel()
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        ScalingLazyColumn(
            horizontalAlignment = Alignment.Start,
//            verticalArrangement = Arrangement.spacedBy(15.dp),
        ){
            item(){
                    Chip(
                        onClick = { navController.navigate(Screen.Stimula.route) },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.watch_vibrate),
                                contentDescription = "Stimulans",
                                modifier = Modifier.size(width = 25.dp, height = 25.dp)
                            )
                        },
                        label = {
//                            Spacer(modifier = Modifier.width(10.dp))
                            Text("Stimulans")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
            }
            item() {
                    Chip(
                        onClick = { navController.navigate(Screen.OperatingHours.route) },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.workhours),
                                contentDescription = "Werkingsuren",
                                modifier = Modifier.size(width = 25.dp, height = 25.dp)
                            )
                        },
                        label = {
//                            Spacer(modifier = Modifier.width(10.dp))
                            Text("Werkingsuren")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
            }
            item(){
                Chip(
                    onClick = { navController.navigate(Screen.Session.route) },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.counter),
                            contentDescription = "Aantal sessies",
                            modifier = Modifier.size(width = 25.dp, height = 25.dp)
                        )
                    },
                    label = {
//                            Spacer(modifier = Modifier.width(10.dp))
                        Text("Aantal sessies")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
package com.example.neglectapp.presentation.ui.settings

import android.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.wear.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.example.neglectapp.R
import com.example.neglectapp.presentation.navigation.Screen
import com.example.neglectapp.presentation.util.ButtonType
@Composable
private fun SettingItem(
    icon: Painter,
    title: String
){

}



@Composable
fun DisplaySettings(
    status: Boolean = false,
    modifier: Modifier,
    navController: NavHostController,
    settingsViewModel: SettingsViewModel = viewModel()
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
            item(){
                Chip(
                    onClick = {},
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.counter),
                            contentDescription = "",
                            modifier = Modifier.size(width = 25.dp, height = 25.dp)
                        )
                    },
                    label = {
//                            Spacer(modifier = Modifier.width(10.dp))
                        Text("Light value=" + settingsViewModel.light.collectAsState().value )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item(){
                Chip(
                    onClick = { navController.navigate(Screen.Alarm.route)},
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.counter),
                            contentDescription = "",
                            modifier = Modifier.size(width = 25.dp, height = 25.dp)
                        )
                    },
                    label = {
//                            Spacer(modifier = Modifier.width(10.dp))
                        Text("AlarmScreen")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }
}
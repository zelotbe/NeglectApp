package com.example.neglectapp.presentation.ui.settings.stimulans

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*

@Composable
fun DisplayStimula(
    status: Boolean = false,
    modifier: Modifier,
    navController: NavHostController
){
    var checked by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScalingLazyColumn(
            horizontalAlignment = Alignment.Start,
        ){
            item(){
                SplitToggleChip(
                    checked = checked,
                    onCheckedChange = {},
                    onClick = {  },
                    toggleControl = {
                        Switch(
                            checked = checked,
                            onCheckedChange = { checked = it }
                        )
                    },
                    label = {
                        Text("Vibratie")
                    },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item() {
                SplitToggleChip(
                    checked = checked,
                    onCheckedChange = {},
                    onClick = {  },
                    toggleControl = {
                        Switch(
                            checked = checked,
                            onCheckedChange = { checked = it }
                        )
                    },
                    label = {
                        Text("Geluid")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item(){
                SplitToggleChip(
                    checked = checked,
                    onCheckedChange = {},
                    onClick = {  },
                    toggleControl = {
                        Switch(
                            checked = checked,
                            onCheckedChange = { checked = it }
                        )
                    },
                    label = {
                        Text("Licht")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
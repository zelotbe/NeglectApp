package com.example.neglectapp.presentation.ui.settings

import androidx.compose.foundation.layout.*
import androidx.wear.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.example.neglectapp.R
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
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ScalingLazyColumn(){
            item(){
                Row() {
                    Icon(
                        painter = painterResource(id = R.drawable.watch_vibrate),
                        contentDescription = "Stimulans",
                        modifier = Modifier.size(width = 25.dp, height = 25.dp)
                    )
                    Text("Stimulans")
                }

            }
            item() {
                Row() {
                    Icon(
                        painter = painterResource(id = R.drawable.workhours),
                        contentDescription = "Werkingsuren",
                        modifier = Modifier.size(width = 25.dp, height = 25.dp)
                    )
                    Text("Werkingsuren")
                }
            }
            item(){
                Row() {
                    Icon(
                        painter = painterResource(id = R.drawable.counter),
                        contentDescription = "Aantal Sessies",
                        modifier = Modifier.size(width = 25.dp, height = 25.dp)
                    )
                    Text("Aantal Sessies")
                }
            }
        }

    }
}
package com.example.neglectapp.ui.data

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Download
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.neglectapp.navigation.Screen

@Composable
fun DisplayData(
    navController: NavHostController,
    modifier: Modifier,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        Chip(
            onClick = {  },
            icon = {
                Icon(
                    Icons.Default.Download,
                    contentDescription = "Lokaal Opslaan",
                    modifier = Modifier.size(width = 25.dp, height = 25.dp)
                )
            },
            label = {
//                            Spacer(modifier = Modifier.width(10.dp))
                Text("Lokaal opslaan")
            },
            modifier = Modifier.width(180.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Chip(
            onClick = { },
            icon = {
                Icon(
                    Icons.Default.CloudUpload,
                    contentDescription = "Upload online",
                    modifier = Modifier.size(width = 25.dp, height = 25.dp)
                )
            },
            label = {
//                            Spacer(modifier = Modifier.width(10.dp))
                Text("Upload online")
            },
            modifier = Modifier.width(180.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text("Laatste upload:")
        Text("DATUM")
    }
}

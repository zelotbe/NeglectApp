package com.example.neglectapp.ui.data

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Download
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.neglectapp.core.Constants
import com.example.neglectapp.service.ServiceHelper
import com.example.neglectapp.viewmodel.HeftosViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DisplayData(
    navController: NavHostController,
    modifier: Modifier,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel: HeftosViewModel = viewModel()
    val lastSynced = viewModel.lastSynced.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        Chip(
            onClick = {
                ServiceHelper.triggerForegroundService(
                    context = context,
                    action = Constants.ACTION_SAVE_LOCAL
                ); Toast.makeText(context, "Opgeslagen", Toast.LENGTH_SHORT).show()
            },
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
            onClick = { ServiceHelper.triggerForegroundService(
                context = context,
                action = Constants.ACTION_SAVE_ONLINE
            ); Toast.makeText(context, "Bestand verzonden", Toast.LENGTH_SHORT).show()},
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

        Text("Laatste upload:", fontSize = 12.sp)
        Text(lastSynced, fontSize = 12.sp)
    }
}
